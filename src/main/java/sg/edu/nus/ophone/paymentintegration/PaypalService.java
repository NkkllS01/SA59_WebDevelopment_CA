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

    // creating PayPal payment in accordance to their requested format
    public Payment createPayment (Order order, String cancelUrl, String successUrl) throws PayPalRESTException{

        // creating an amount object formatted to 2d.p., with fixed currency of SGD
        Amount amount = new Amount();
        amount.setTotal(String.format("%.2f", order.getTotalAmount()));
        amount.setCurrency("SGD");

        // creating a transaction to represent the payment transaction, with fixed description and amount attached
        Transaction transaction = new Transaction();
        transaction.setDescription("Payment for Order ID: " + order.getId());
        transaction.setAmount(amount);

        // PayPal requires a list of transactions even if there is only one transaction
        // here regardless of how many items in an order there is only one transaction
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // creating a payer object, with fixed payment method of "PayPal"
        Payer payer = new Payer();
        payer.setPaymentMethod("PayPal");

        // creating a payment object, with fixed intent of "sale" and payer, transactions attached
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // setting the redirect URLs for different stat of the payment approval process
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        System.out.println("Creating payment with details: " + payment.toJSON());

        // sends a request to the PayPal API to create the payment
        return payment.create(apiContext);
    }

    // following the successUrl, will call upon this method to execute the payment
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        // sends a request to PayPal to finalize and execute the payment using the provided paymentId and payerId
        return payment.execute(apiContext, paymentExecute);
    }
}
