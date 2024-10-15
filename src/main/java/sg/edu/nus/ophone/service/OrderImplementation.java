package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.OrderInterface;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.model.PaymentRecord;
import sg.edu.nus.ophone.repository.OrderDetailsRepository;
import sg.edu.nus.ophone.repository.OrderRepository;


import java.util.List;

/**
 *
 * Creating, Cancel, RetriveOrder(by userId or orderId), and confirm
 * order details in the cart system.
 *
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

@Service
@Transactional
public class OrderImplementation implements OrderInterface {
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderDetailsRepository orderDetailsRepo;


    @Override
    public List<Order> findByUserId(long userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order findByOrderId(Long orderId) {
        return orderRepo.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetails> findByOrder(Order order) {
        return orderDetailsRepo.findByOrder(order);
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    @Override
    public Order findByOrderIdAndUserId(Long orderId, int userId) {
        return orderRepo.findByOrderIdAndUserId(orderId, userId);
    }

    @Override
    public void cancelOrder(Order order) {
        order.setOrderStatus("Cancelled");
        order.getShipping().setShippingStatus("Cancelled");
        PaymentRecord payment = order.getPayment();
        String paymentStatus = payment.getStatus();
        if (!paymentStatus.equalsIgnoreCase("Completed")) {
            payment.setStatus("Cancelled");
        } else {
            payment.setStatus("Pending refund");
        }
    }
    
    @Override
    public OrderDetails addOrderDetail(OrderDetails orderDetail) {
		return orderDetailsRepo.save(orderDetail);
	}
	
	@Override
    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId){
		return orderDetailsRepo.findAllByOrderId(orderId);
	}
	
	@Override
	public OrderDetails updateOrderDetail(OrderDetails orderDetail) {
		return orderDetailsRepo.save(orderDetail);
	}
	
	@Override
	@Transactional
	public void updateQuantity(Long userId,Long productId,Integer quantity) {
		OrderDetails orderDetail =orderDetailsRepo.findByUserIdAndProductId(userId, productId);
		if(orderDetail!=null) {
			orderDetail.setQuantity(quantity);
			orderDetailsRepo.save(orderDetail);
		}
	}

    @Override
    public void createOrder(Order order) {
        orderRepo.save(order);
    }
	
	@Override
	@Transactional
	public boolean removeOrderDetail(Long orderId, Long productId) {
	    try {
	        int deletedCount = orderDetailsRepo.deleteByOrderIdAndProductId(orderId, productId);
	        return deletedCount > 0;  // if Count>0,then the delete success
	    } catch (Exception e) {
	        // record exception
	        System.out.println("Error while removing order detail: " + e.getMessage());
	        return false;
	    }
	}

    @Override
    public Order getCartByUserId(Long userId) {
        return orderRepo.findByUserIdAndStatus(userId, "cart");
    }

    @Override
    public void submitOrder(Long userId) {
        Order cart = orderRepo.findByUserIdAndStatus(userId, "cart");
        if (cart != null) {

            orderRepo.save(cart);
        } else {
            throw new IllegalArgumentException("not found cart");
        }
    }
}
