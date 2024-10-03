package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Order;
import sg.edu.nus.ophone.repository.OrderRepository;
import sg.edu.nus.ophone.repository.OrderStatusRepository;

import java.util.ArrayList;
import java.util.List;

//code by Team3.Chen Sirui
@SpringBootTest
public class OrderDataCreation {
    @Autowired
    private OrderRepository OrdRepo;


    @Test
    void OrderCreation() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("202410001", "RX782", "2024-10-1", 8848.0));
        orders.add(new Order("202410002", "RX79", "2024-10-3", 6666.0));
        orders.add(new Order("202410003", "RTX4090", "2024-10-2", 999.0));
        orders.add(new Order("202410004", "HK416D", "2024-10-31", 11451.4));
        // status setting not implemented

        OrdRepo.saveAll(orders);
    }
}
