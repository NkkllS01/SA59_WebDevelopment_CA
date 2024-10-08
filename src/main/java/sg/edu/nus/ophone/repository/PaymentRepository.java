package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Payment;

//code by Team3.Kuo Chi
@Repository
public interface PaymentRepository extends JpaRepository <Payment, Integer> {
    Payment findByPaypalId(String paypalId);
}
