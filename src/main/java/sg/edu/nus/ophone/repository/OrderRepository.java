package sg.edu.nus.ophone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;

//code by Team3.Chen Sirui
@Repository
public interface OrderRepository extends JpaRepository <Order,Long> {
    @Query("select o from Order o where o.user.id = :uid")
    public List<Order> findByUserId(@Param("uid") long uid);

    @Query("SELECT o from Order o WHERE o.id = :id")
    public Order findByOrderId(@Param("id") Long orderId);

    @Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.user.id = :userId")
    public Order findByOrderIdAndUserId(@Param("orderId")Long orderId, @Param("userId")int userId);


}
