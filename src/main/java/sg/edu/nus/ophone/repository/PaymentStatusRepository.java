package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.ophone.model.PaymentStatus;

//code by Team3.Kuo Chi
public interface PaymentStatusRepository extends JpaRepository <PaymentStatus, Integer> {

}
