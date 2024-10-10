package sg.edu.nus.ophone.interfacemethods;
import java.util.List;

import com.paypal.api.payments.Order;

/**
 * 
 * Creating, Cancel, RetriveOrder(by userId or orderId), and confirm
 * order details in the cart system.
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */
public interface OrderService {
	
	Order createOrder(Order order);
	
	Order getOrderById(Long orderId);
	
	void cancelOrder(Long orderId);
	
	Order getCartByUserId(Long userId);
	
	List<Order> getOrdersByUserId(Long userId);
	
	public void submitOrder(Long userId);
	
}
