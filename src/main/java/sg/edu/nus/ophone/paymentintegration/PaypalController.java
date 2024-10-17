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

    @PostMapping("/paypal")
    public String payment(HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            System.out.println("Retrieved username from session for Paypal: " + username);

            User user = userServiceImp.findByName(username);

            if (user == null) {
                return "redirect:/cart";
            }

            long userId = user.getId();
            List<Order> orders = orderImplementation.findByUserId(userId);

            Optional<Order> optionalOrder = orders.stream()
                    .filter(order -> "Cart".equals(order.getOrderStatus()))
                    .findFirst();

            if (!optionalOrder.isPresent()) {
                return "redirect:/cart";
            }

            Order order = optionalOrder.get();

            if (order == null) {
                return "redirect:/cart";
            }
            System.out.println("Retrieved related Order");

            Payment paypalPayment = paypalService.createPayment(order, "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            String paymentId = paypalPayment.getId();

            PaymentRecord paymentRecord = paymentService.createPaymentRecord(order, "Validating", paymentId);

            for(Links link:paypalPayment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "payment-fail";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(@RequestParam("paymentId") String paymentId) {
        paymentService.updatePaymentStatus(paymentId, "Cancelled");
        return "payment-cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(HttpSession session, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment paypalPayment = paypalService.executePayment(paymentId, payerId);
            System.out.println(paypalPayment.toJSON());
            if (paypalPayment.getState().equals("approved")) {
                paymentService.updatePaymentStatus(paymentId, "Completed");

                String username = (String) session.getAttribute("username");

                User user = userServiceImp.findByName(username);
                if (user == null) {
                    return "redirect:/cart";
                }

                long userId = user.getId();
                List<Order> orders = orderImplementation.findByUserId(userId);

                Optional<Order> optionalOrder = orders.stream()
                        .filter(order -> "Cart".equals(order.getOrderStatus()))
                        .findFirst();

                if (!optionalOrder.isPresent()) {
                    return "redirect:/cart";
                }

                Order order = optionalOrder.get();

                if (order == null) {
                    return "redirect:/cart";
                } else {
                    order.setOrderStatus("Completed");
                }

                return "payment-success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        paymentService.updatePaymentStatus(paymentId, "Failed");
        return "payment-fail";
    }

}

