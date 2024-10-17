package sg.edu.nus.ophone.paymentintegration;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpSession;
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

//Team3.Kuo Chi
@Controller
public class PaypalController {

    @Autowired
    private PaypalService paypalService;
    private PaymentService paymentService;
    private OrderImplementation orderImplementation;

    public static final String SUCCESS_URL = "payment/success";
    public static final String CANCEL_URL = "payment/cancel";

    @PostMapping("/paypal")
    public String payment(HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");

            if (user == null) {
                return "redirect:/cart";
            }

            long userId = user.getId();
            Order order = (Order) orderImplementation.findByUserId(userId);

            if (order == null) {
                return "redirect:/cart";
            }

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
        return "fail";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(@RequestParam("paymentId") String paymentId) {
        paymentService.updatePaymentStatus(paymentId, "Cancelled");
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment paypalPayment = paypalService.executePayment(paymentId, payerId);
            System.out.println(paypalPayment.toJSON());
            if (paypalPayment.getState().equals("approved")) {
                paymentService.updatePaymentStatus(paymentId, "Completed");
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        paymentService.updatePaymentStatus(paymentId, "Failed");
        return "fail";
    }

}

