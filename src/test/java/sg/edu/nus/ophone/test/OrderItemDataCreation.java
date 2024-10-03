package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.model.OrderItem;
import sg.edu.nus.ophone.repository.OrderItemRepository;
import sg.edu.nus.ophone.repository.OrderRepository;
import sg.edu.nus.ophone.repository.ProductRepository;

//code by Team3.Lian Da
@SpringBootTest
public class OrderItemDataCreation {

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Test
    void conTextLoad() {

        Order order1 = orderRepo.findById(1L).orElse(null);
        Order order2 = orderRepo.findById(2L).orElse(null);
        Product product1 = productRepo.findById(1L).orElse(null);
        Product product2 = productRepo.findById(2L).orElse(null);

        OrderItem orderItem1 = new OrderItem(order1, product1, 5, 100.0);
        OrderItem orderItem2 = new OrderItem(order1, product2, 3, 150.0);
        OrderItem orderItem3 = new OrderItem(order2, product1, 2, 200.0);
        OrderItem orderItem4 = new OrderItem(order2, product2, 1, 250.0);

        orderItemRepo.save(orderItem1);
        orderItemRepo.save(orderItem2);
        orderItemRepo.save(orderItem3);
        orderItemRepo.save(orderItem4);
    }
}
