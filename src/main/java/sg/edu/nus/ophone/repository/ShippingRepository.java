package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Shipping;

import java.util.List;

//code by Team3.Gao Zijie
@Repository
public interface ShippingRepository extends JpaRepository <Shipping, Integer> {
    public Shipping findByOrderId(int orderId);
}
