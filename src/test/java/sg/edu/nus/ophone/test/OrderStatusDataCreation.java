package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.OrderStatus;
import sg.edu.nus.ophone.repository.OrderStatusRepository;

import java.util.ArrayList;
import java.util.List;

//code by Team3.Chen Sirui
@SpringBootTest
public class OrderStatusDataCreation {
    @Autowired
    private OrderStatusRepository OrdStatRepo;

    @Test
    public void statusCreation() {
        List<OrderStatus> statuses = new ArrayList<>();
        statuses.add(new OrderStatus(1, "In Cart"));// in cart
        statuses.add(new OrderStatus(2, "Pending"));// placed but not paid
        statuses.add(new OrderStatus(3, "Processing "));// placed and paid
        statuses.add(new OrderStatus(4, "Shipped"));// out for shipping but not delivered
        statuses.add(new OrderStatus(5, "Delivered"));// delivered to customer
        statuses.add(new OrderStatus(6, "Cancelled"));//
        statuses.add(new OrderStatus(7, "Returned"));// returned but not refunded
        statuses.add(new OrderStatus(8, "Refunded"));//
        statuses.add(new OrderStatus(9, "Completed"));//

        OrdStatRepo.saveAll(statuses);
    }
}
