package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.PaymentMethod;

//code by Team3.Kuo Chi
@Repository
public interface PaymentMethodRepository extends JpaRepository <PaymentMethod, Integer> {

}
