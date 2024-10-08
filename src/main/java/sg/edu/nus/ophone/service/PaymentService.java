package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.Payment;
import sg.edu.nus.ophone.repository.PaymentRepository;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment createPaymentRecord(String orderId, double amount, String status) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentDate(LocalDateTime.now().toString());
        payment.setPaymentAmount(amount);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Transactional
    public void updatePaymentStatus(Payment payment, String status) {
        payment.setStatus(status);
        paymentRepository.save(payment);
    }





}
