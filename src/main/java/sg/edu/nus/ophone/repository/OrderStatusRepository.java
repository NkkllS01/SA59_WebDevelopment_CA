package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.OrderStatus;

//code by Team3.Chen Sirui
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
	
}
