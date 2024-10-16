package sg.edu.nus.ophone.paymentintegration;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.PaymentRecord;
import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.service.OrderImplementation;
import sg.edu.nus.ophone.service.PaymentService;
import sg.edu.nus.ophone.service.UserServiceImp;


@Controller
public class PaypalController {

    @Autowired
    private PaypalService paypalService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderImplementation orderImplementation;
    @Autowired
    private UserServiceImp userServiceImp;

    public static final String SUCCESS_URL = "payment-success";
    public static final String CANCEL_URL = "payment-cancel";

    // initiates the PayPal payment process
    @PostMapping("/paypal")
    public String payment(HttpSession session) {
        try {
            // obtain the username stored in session
            String username = (String) session.getAttribute("username");
            System.out.println("Retrieved username from session for Paypal: " + username);

            // find the user object from the username
            User user = userServiceImp.findByName(username);

            // redirect to /cart if user is null
            if (user == null) {
                return "redirect:/cart";
            }

            // get the userId from the user object
            long userId = user.getId();

            // find the list of orders by userId
            List<Order> orders = orderImplementation.findByUserId(userId);
            // filter through the list where order status = "Cart"
            Optional<Order> optionalOrder = orders.stream()
                    .filter(order -> "Cart".equals(order.getOrderStatus()))
                    .findFirst();

            // if there is no order fulfilling the criteria, redirect to /cart
            if (!optionalOrder.isPresent()) {
                return "redirect:/cart";
            }

            // extracts the order object from optionalOrder and create shipping record
            Order order = optionalOrder.get();

            System.out.println("Retrieved related Order");

            // create a PayPal payment
            Payment paypalPayment = paypalService.createPayment(order, "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            // get paypalPaymentId
            String paymentId = paypalPayment.getId();

            // create payment record on our server
            PaymentRecord paymentRecord = paymentService.createPaymentRecord(order, "Validating", paymentId);

            // redirects the user to the PayPal approval page to confirm the payment
            for(Links link:paypalPayment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        // if there's a PayPalRESTException, redirect to /payment/fail
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "payment-fail";
    }

    // if the user cancels the payment on PayPal approval page, will redirect to /payment/cancel
    @GetMapping(value = CANCEL_URL)
    public String cancelPay(@RequestParam("paymentId") String paymentId) {
        paymentService.updatePaymentStatus(paymentId, "Cancelled");
        return "payment-cancel";
    }

    // if the user completes the payment, redirect to /payment/success
    @GetMapping(value = SUCCESS_URL)
    public String successPay(HttpSession session, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            // execute the payment using the paymentId and payerId from PayPal response
            Payment paypalPayment = paypalService.executePayment(paymentId, payerId);
            System.out.println(paypalPayment.toJSON());

            // if the state is "approved" from PayPal response, update the payment status in the payment record
            if (paypalPayment.getState().equals("approved")) {
                paymentService.updatePaymentStatus(paymentId, "Completed");

                // obtain the username stored in session
                String username = (String) session.getAttribute("username");

                // find the user object from the username
                User user = userServiceImp.findByName(username);

                // redirect to /cart if user is null
                if (user == null) {
                    return "redirect:/cart";
                }

                // get the userId from the user object
                long userId = user.getId();

                // find the list of orders by userId
                List<Order> orders = orderImplementation.findByUserId(userId);
                // filter through the list where order status = "Cart"
                Optional<Order> optionalOrder = orders.stream()
                        .filter(order -> "Cart".equals(order.getOrderStatus()))
                        .findFirst();

                // if there is no order fulfilling the criteria, redirect to /cart
                if (!optionalOrder.isPresent()) {
                    return "redirect:/cart";
                }

                // extracts the order object from optionalOrder and create shipping record
                Order order = optionalOrder.get();
                // update the order status to "Completed"
                order.setOrderStatus("Completed");

                return "payment-success";
            }
        }
        // if there's a PayPalRESTException, redirect to /payment/fail
        catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        paymentService.updatePaymentStatus(paymentId, "Failed");
        return "payment-fail";
    }

}

