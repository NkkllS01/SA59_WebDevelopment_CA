package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

//code by Team3.Chen Sirui
@SpringBootTest
public class OrderStatusDataCreation {
    @Autowired
    private OrderStatusDataCreation OrdStatRepo;

    @Test
    public void statusCreation() {
        List<OrderStatus> statuses = new ArrayList<>();
        statuses.add(new OrderStatus(1, "Pending"));// placed but not paid
        statuses.add(new OrderStatus(2, "Processing "));// placed and paid
        statuses.add(new OrderStatus(3, "Shipped"));// out for shipping but not delivered
        statuses.add(new OrderStatus(4, "Delivered"));// delivered to customer
        statuses.add(new OrderStatus(5, "Cancelled"));//
        statuses.add(new OrderStatus(6, "Returned"));// returned but not refunded
        statuses.add(new OrderStatus(7, "Refunded"));//
        statuses.add(new OrderStatus(8, "Completed"));//

        statuses.forEach(e -> OrdStatRepo.save(e));
    }
}
