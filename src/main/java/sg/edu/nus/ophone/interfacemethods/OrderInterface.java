package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.repository.OrderDetailsRepository;

import java.util.List;

public interface OrderInterface {
    public List<Order> findByUserId(String userId);
    public Order findByOrderId(int orderId);
    public List<OrderDetails> findByOrder(Order order);
}
