package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.model.OrderDetailsId;


import java.util.List;
/**
 * 
 * 
 * OrderDetailRepository
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Long>{
	
	List<OrderDetails> findAllByOrderId(Long orderId);
	OrderDetails findByOrderIdAndProductId(Long orderId, Long productId);
	
    @Query("SELECT od FROM OrderDetails od WHERE od.order = :order")
    public List<OrderDetails> findByOrder(@Param("order") Order order);
    
    @Modifying
	@Transactional
	@Query("DELETE FROM OrderDetail od WHERE od.order.id = :orderId AND od.product.id = :productId")
	int deleteByOrderIdAndProductId(@Param("orderId") Long orderId, @Param("productId") Long productId);

	
	

	 @Query("SELECT od FROM OrderDetail od WHERE od.order.user.id = :userId AND od.product.id = :productId")
	    OrderDetails findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
    

}

