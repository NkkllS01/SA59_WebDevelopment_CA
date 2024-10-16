package sg.edu.nus.ophone.paymentintegration;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.model.Order;

import java.util.ArrayList;
import java.util.List;

//Team3.Kuo Chi
@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;


    public Payment createPayment (Order order, String cancelUrl, String successUrl) throws PayPalRESTException{

        Amount amount = new Amount();
        amount.setTotal(String.format("%.2f", order.getTotalAmount()));
        amount.setCurrency("SGD");

        Transaction transaction = new Transaction();
        transaction.setDescription("Payment for Order ID: " + order.getId());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("PayPal");

        Payment payment = new Payment();
        payment.setIntent("SALE");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);

    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

}
