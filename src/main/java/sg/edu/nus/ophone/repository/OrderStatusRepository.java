package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.ophone.model.OrderStatus;

//code by Team3.Chen Sirui
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
	
}
