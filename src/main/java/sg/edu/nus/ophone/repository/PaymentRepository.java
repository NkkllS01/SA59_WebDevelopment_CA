package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.PaymentRecord;

import java.util.List;

//code by Team3.Kuo Chi
@Repository
public interface PaymentRepository extends JpaRepository <PaymentRecord, Integer> {
    public PaymentRecord findByPaypalId(String paypalId);
}
