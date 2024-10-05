package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.OrderInterface;
import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.OrderDetails;
import sg.edu.nus.ophone.repository.OrderDetailsRepository;
import sg.edu.nus.ophone.repository.OrderRepository;

import java.util.List;

@Service
@Transactional
public class OrderImplementation implements OrderInterface {
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailsRepository orderDetailsRepo;

    @Override
    public List<Order> findByUserId(String userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order findByOrderId(int orderId) {
        return orderRepo.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetails> findByOrder(Order order) {
        return orderDetailsRepo.findByOrder(order);
    }
}
