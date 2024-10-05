package sg.edu.nus.ophone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Order;

//code by Team3.Chen Sirui
@Repository
public interface OrderRepository extends JpaRepository <Order,Integer> {
    @Query("select o from Order o where o.user.id = :uid")
    List<Order> findByUserId(@Param("uid") String uid);


}
