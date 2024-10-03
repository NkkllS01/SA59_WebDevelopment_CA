package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.ophone.model.Payment;

//code by Team3.Kuo Chi
public interface PaymentRepository extends JpaRepository <Payment, Integer> {

}
