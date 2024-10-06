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
}
