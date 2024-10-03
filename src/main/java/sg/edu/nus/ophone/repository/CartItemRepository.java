package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.ophone.model.CartItem;

//code by Team3.Yu Yifan
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}

