package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.repository.OrderDetailsRepository;

import java.util.List;
import java.util.LongSummaryStatistics;

public interface OrderInterface {
    public List<Order> findByUserId(long userId);
    public Order findByOrderId(Long orderId);
    public List<OrderDetails> findByOrder(Order order);
    public void save(Order order);
    public Order findByOrderIdAndUserId(Long orderId, int userId);
    public void cancelOrder(Order order);
    
    OrderDetails addOrderDetail(OrderDetails orderDetail);
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);
    boolean removeOrderDetail(Long orderId, Long productId);
    void updateQuantity(Long orderId, Long productId, Integer quantity);
    OrderDetails updateOrderDetail(OrderDetails orderDetail);
    public Order getCartByUserId(Long userId);
    void createOrder(Order order);
    void submitOrder(Long userId);
}
