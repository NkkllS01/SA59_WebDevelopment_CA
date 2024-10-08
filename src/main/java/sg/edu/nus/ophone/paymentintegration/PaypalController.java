package sg.edu.nus.ophone.paymentintegration;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.service.PaymentService;


@Controller
public class PaypalController {

    @Autowired
    private PaypalService paypalService;
    private PaymentService paymentService;

    public static final String SUCCESS_URL = "payment/success";
    public static final String CANCEL_URL = "payment/cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {

            Payment paypalPayment = paypalService.createPayment(order, "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);

            sg.edu.nus.ophone.model.Payment paymentRecord = paymentService.createPaymentRecord(order.getId(), order.getTotalAmount(), "Validating");

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
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment paypalPayment = paypalService.executePayment(paymentId, payerId);
            System.out.println(paypalPayment.toJSON());
            if (paypalPayment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "fail";
    }

}
