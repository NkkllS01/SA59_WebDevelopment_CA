package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.OrderDetails;

//code by Team3.Lian Da
@Repository
public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Integer>{

}
