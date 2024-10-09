package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.PaymentRecord;
import sg.edu.nus.ophone.repository.PaymentRepository;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public PaymentRecord createPaymentRecord(Order order, String status) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setOrder(order);
        paymentRecord.setPaymentDate(LocalDateTime.now().toString());
        paymentRecord.setStatus(status);
        return paymentRepository.save(paymentRecord);
    }

    @Transactional
    public void updatePaymentStatus(PaymentRecord paymentRecord, String status) {
        paymentRecord.setStatus(status);
        paymentRepository.save(paymentRecord);
    }





}
