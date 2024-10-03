package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.ophone.model.PaymentMethod;

//code by Team3.Kuo Chi
public interface PaymentMethodRepository extends JpaRepository <PaymentMethod, Integer> {

}
