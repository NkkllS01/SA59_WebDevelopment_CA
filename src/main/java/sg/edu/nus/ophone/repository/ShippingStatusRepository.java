package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.ShippingStatus;

//code by Team3.Gao Zijie
@Repository
public interface ShippingStatusRepository extends JpaRepository <ShippingStatus, Integer> {

}
