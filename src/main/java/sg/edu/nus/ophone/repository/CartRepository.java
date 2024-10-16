package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Cart;

//code by Team3.Yu Yifan
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
