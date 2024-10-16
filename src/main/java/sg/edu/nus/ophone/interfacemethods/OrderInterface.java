package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.repository.OrderDetailsRepository;

import java.util.List;
import java.util.LongSummaryStatistics;

public interface OrderInterface {
    List<Order> findByUserId(long userId);
    Order findByOrderId(Long orderId);
    List<OrderDetails> findByOrder(Order order);
    void save(Order order);
    Order findByOrderIdAndUserId(Long orderId, int userId);
    void cancelOrder(Order order);
    
    OrderDetails addOrderDetail(OrderDetails orderDetail);
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);
    boolean removeOrderDetail(Long orderId, Long productId);
    void updateQuantity(Long orderId, Long productId, Integer quantity);
    OrderDetails updateOrderDetail(OrderDetails orderDetail);
    Order getCartByUserId(Long userId);
    void createOrder(Order order);
    void submitOrder(Long userId);
}
