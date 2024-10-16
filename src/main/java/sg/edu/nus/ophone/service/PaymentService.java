package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.PaymentRecord;
import sg.edu.nus.ophone.repository.PaymentRepository;

import java.time.LocalDateTime;

//Team3.Kuo Chi
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // creates a payment record on our server
    @Transactional
    public PaymentRecord createPaymentRecord(Order order, String status, String paymentId) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setOrder(order);
        paymentRecord.setPaymentAmount(order.getTotalAmount());
        paymentRecord.setPaymentDate(LocalDateTime.now().toString());
        paymentRecord.setStatus(status);
        paymentRecord.setPaypalId(paymentId);
        return paymentRepository.save(paymentRecord);
    }

    // updates payment status upon the completion, cancellation or failure of payment
    @Transactional
    public void updatePaymentStatus(String paypalId, String status) {
        PaymentRecord record = paymentRepository.findByPaypalId(paypalId);
        if (record != null) {
            record.setStatus(status);
            paymentRepository.save(record);
        }
    }

}
