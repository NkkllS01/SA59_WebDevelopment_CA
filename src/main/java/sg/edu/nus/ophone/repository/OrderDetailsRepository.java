package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;

import java.util.List;

//code by Team3.Lian Da
@Repository
public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Long>{
    @Query("SELECT od FROM OrderDetails od WHERE od.order = :order")
    public List<OrderDetails> findByOrder(@Param("order") Order order);
}
