package sg.edu.nus.ophone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.repository.OrderRepository;
import sg.edu.nus.ophone.interfacemethods.OrderService;

/**
 * 
 * Creating, Cancel, RetriveOrder(by userId or orderId), and confirm
 * order details in the cart system.
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order createOrder(Order order) {
		if("cart".equals(order.getStatus())) {
			order.setStatus("order");
		}
		return orderRepository.save(order);
	}
	
	@Override
    public void cancelOrder(Long orderId) {
		Optional<Order> order=orderRepository.findById(orderId);
		if(order.isPresent()) {
			Order o=order.get();
			o.setStatus("canceled");
			orderRepository.save(o);
			}
		
	}
	
	@Override
    public Order getCartByUserId(Long userId) {
        return orderRepository.findByUserIdAndStatus(userId, "cart");
    }
	
	 @Override
	public Order getOrderById(Long orderId) {
		 return orderRepository.findById(orderId).orElse(null);
	 }
	 
	  @Override
	    public List<Order> getOrdersByUserId(Long userId) {
	        return orderRepository.findByUserId(userId);
	    }
	  
	    @Override
	    public void submitOrder(Long userId) {
	        Order cart = orderRepository.findByUserIdAndStatus(userId, "cart");
	        if (cart != null) {
	            cart.setStatus("submitted");
	            orderRepository.save(cart);
	        } else {
	            throw new IllegalArgumentException("not found cart");
	        }
	    }
}
