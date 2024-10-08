package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sg.edu.nus.ophone.model.*;
import sg.edu.nus.ophone.repository.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestDataCreation {
    @Autowired
    BrandRepository brandRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    CartItemRepository cartItemRepo;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderDetailsRepository orderDetailsRepo;
    @Autowired
    PaymentMethodRepository paymentMethodRepo;
    @Autowired
    PaymentRepository paymentRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    ReviewRepository reviewRepo;
    @Autowired
    ShippingRepository shippingRepo;
    @Autowired
    UserRepository userRepo;

    @Test
    void contextLoad() {
        // Create User Data
        User andy = new User("Customer", "Andy", "andy@email.com", "password",
                "20 Holland Village Road", "Singapore", "233020");
        User billy = new User("Customer", "Billy", "billy@email.com", "password",
                "3 Robinson Ring", "Singapore", "342503");
        User candy = new User("Staff", "Candy", "candy@email.com", "password",
                "123 Jurong West #05-28", "Singapore", "668123");
        userRepo.saveAll(Arrays.asList(andy, billy, candy));

        // Create Brand Data
        Brand orange = new Brand("Orange", "Orange");
        Brand pineapple = new Brand("Pineapple", "Pineapple");
        Brand kiwi = new Brand("Kiwi", "Kiwi");
        brandRepo.saveAll(Arrays.asList(orange, pineapple, kiwi));

        // Create Product Data
        Product oPhone = new Product("Orange oPhone 24", "oPhone 24", 1500.00, 40, orange);
        Product pPhone = new Product("Pineapple pPhone Pro", "pPhone Pro", 1900.00, 30, pineapple);
        Product kPhone = new Product("Kiwi kPhone S3", "kPhone S3", 2024, 99, kiwi);
        productRepo.saveAll(Arrays.asList(oPhone, pPhone, kPhone));

        // Create Order Data
        Order order1 = new Order(andy, "2024-10-01");
        Order order2 = new Order(billy, "2024-10-03");
        Order order3 = new Order(andy, "2024-10-02");
        Order order4 = new Order(billy, "2024-10-05");
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4));

        // Create Order Details Data
        OrderDetails order1_p1 = new OrderDetails(order1, oPhone, 3);
        OrderDetails order1_p2 = new OrderDetails(order1, pPhone, 2);
        OrderDetails order2_p1 = new OrderDetails(order2, pPhone, 2);
        OrderDetails order2_p2 = new OrderDetails(order2, kPhone, 3);
        OrderDetails order3_p1 = new OrderDetails(order3, pPhone, 1);
        OrderDetails order4_p1 = new OrderDetails(order4, oPhone, 2);
        OrderDetails order4_p2 = new OrderDetails(order4, pPhone, 1);
        OrderDetails order4_p3 = new OrderDetails(order4, kPhone, 1);
        orderDetailsRepo.saveAll(Arrays.asList(order1_p1, order1_p2, order2_p1,
                order2_p2, order3_p1, order4_p1, order4_p2, order4_p3));

        // Set OrderDetails for each Order
        order1.setOrderDetails(Arrays.asList(order1_p1, order1_p2));
        order2.setOrderDetails(Arrays.asList(order2_p1, order2_p2));
        order3.setOrderDetails(List.of(order3_p1));
        order4.setOrderDetails(Arrays.asList(order4_p1, order4_p2, order4_p3));
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4));

        // Create Payment Method Data
        PaymentMethod creditCard = new PaymentMethod("Credit Card",
                "Accept credit cards issued by Visa and Mastercard");
        PaymentMethod paypal = new PaymentMethod("Paypal",
                "An online payment system that acts as the intermediary between a payer and payee");
        PaymentMethod applePay = new PaymentMethod("Apple Pay",
                "Works as a mobile wallet that enables a one-click payment option on websites that accept it");
        paymentMethodRepo.saveAll(Arrays.asList(creditCard, paypal, applePay));

        // Create Payment Data
        Payment pay1 = new Payment(order1, "2024-10-01", creditCard, "Completed");
        Payment pay2 = new Payment(order2, "2024-10-03", paypal, "Processing");
        Payment pay3 = new Payment(order3, "2024-10-02", applePay, "Completed");
        Payment pay4 = new Payment(order4, "2024-10-05", creditCard, "Unsuccessful");
        paymentRepo.saveAll(Arrays.asList(pay1, pay2, pay3, pay4));

        // Set Payment for each Order
        order1.setPayment(pay1);
        order2.setPayment(pay2);
        order3.setPayment(pay3);
        order4.setPayment(pay4);
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4));

        // Update Order Status
        order1.setOrderStatus("Pending delivery");
        order2.setOrderStatus("Pending payment");
        order3.setOrderStatus("Pending delivery");
        order4.setOrderStatus("Pending payment");
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4));

        // Create Shipping Data
        Shipping ship1 = new Shipping(order1, "20 Holland Village Road",
                "Singapore", "233020");
        Shipping ship2 = new Shipping(order2, "3 Robinson Ring",
                "Singapore", "342503");
        Shipping ship3 = new Shipping(order3, "25 Heng Mui Keng Terrace",
                "Singapore", "119617");
        Shipping ship4 = new Shipping(order4, "21 Lower Kent Ridge Rd",
                "Singapore", "119077");
        shippingRepo.saveAll(Arrays.asList(ship1, ship2, ship3, ship4));

        // Update Shipping / Order Status and Shipping Date
        ship1.setShippingStatus("Order shipped");
        ship1.setShippingDate("2024-10-02");
        ship3.setShippingStatus("Order shipped");
        ship3.setShippingDate("2024-10-04");
        shippingRepo.saveAll(Arrays.asList(ship1, ship3));

        ship1.setDeliveryDate("2024-10-05");
        ship1.setShippingStatus("Delivered");
        shippingRepo.save(ship1);
        order1.setOrderStatus("Completed");
        orderRepo.save(order1);

        // Create Review Data
        Review review1 = new Review(oPhone, andy, 5, "Excellent product!", "2024-10-05");
        Review review2 = new Review(pPhone, andy, 4, "Very good, but a little pricey.", "2024-10-05");
        reviewRepo.saveAll(Arrays.asList(review1, review2));
    }
}
