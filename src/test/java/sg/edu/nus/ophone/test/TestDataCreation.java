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
    OrderRepository orderRepo;
    @Autowired
    OrderDetailsRepository orderDetailsRepo;
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
        User derek = new User("Customer", "Derek", "derek@email.com", "password",
                "2 Ming Teck Park", "Singapore", "277370");
        User candy = new User("Staff", "Candy", "candy@email.com", "password",
                "123 Jurong West #05-28", "Singapore", "668123");
        userRepo.saveAll(Arrays.asList(andy, billy, candy, derek));

        // Create Brand Data
        Brand orange = new Brand("Orange", "Orange");
        Brand pineapple = new Brand("Pineapple", "Pineapple");
        Brand kiwi = new Brand("Kiwi", "Kiwi");
        Brand huawei = new Brand("Huawei", "Huawei");
        Brand xiaomi = new Brand("Xiaomi", "Xiaomi");
        Brand apple = new Brand("Apple", "Apple");
        brandRepo.saveAll(Arrays.asList(orange, pineapple, kiwi, huawei, xiaomi, apple));

        // Create Product Data
        Product oPhone = new Product("Orange oPhone 24", "oPhone 24", 1500.00, 40, orange);
        Product pPhone = new Product("Pineapple pPhone Pro", "pPhone Pro", 1900.00, 30, pineapple);
        Product kPhone = new Product("Kiwi kPhone S3", "kPhone S3", 2024, 99, kiwi);
        Product kPhone2 = new Product("Kiwi kPhone S1 Max", "kPhone S1 Max", 1999, 56, kiwi);
        Product hwMate = new Product("HUAWEI Mate60", "6.7\" display, Weight: approx. 206g (w/ battery)",
                1600, 90, huawei);
        hwMate.setImagePathName("/images/HUAWEI Mate60.jpg");
        Product hwPura = new Product("HUAWEI Pura70",
                "Ultra Speed Snapshot, Ultra Lighting Macro Telephoto Camera, Super Durable Kunlun Glass, 100W Wired & 80W Wireless SuperCharge",
                1548, 37, huawei);
        hwPura.setImagePathName("/images/HUAWEI Pura70.jpg");
        Product xmFold = new Product("Xiaomi MIX Fold 4",
                "Six Leica-branded cameras, a 5,100mAh battery and the latest Qualcomm chip.",
                1582.50, 34, xiaomi);
        xmFold.setImagePathName("/images/Xiaomi MIX Fold 4.jpg");
        Product xmUltra = new Product("Xiaomi MIX Fold4",
                "Qualcomm Snapdragon 8 Gen 3, powered by modern LPRRD5X RAM and the latest UFS 4.0 storage technologies.",
                1699, 28, xiaomi);
        xmUltra.setImagePathName("/Xiaomi 14Ultra.jpg");
        productRepo.saveAll(Arrays.asList(oPhone, pPhone, kPhone, kPhone2, hwMate, hwPura, xmFold, xmUltra));

        // Create Order Data
        Order order1 = new Order(andy, "2024-10-01");
        Order order2 = new Order(billy, "2024-10-03");
        Order order3 = new Order(andy, "2024-10-02");
        Order order4 = new Order(billy, "2024-10-05");
        Order order5 = new Order(derek, "2024-10-03");
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4, order5));

        // Create Order Details Data
        OrderDetails order1_p1 = new OrderDetails(order1, oPhone, 3);
        OrderDetails order1_p2 = new OrderDetails(order1, pPhone, 2);
        OrderDetails order2_p1 = new OrderDetails(order2, pPhone, 2);
        OrderDetails order2_p2 = new OrderDetails(order2, kPhone, 3);
        OrderDetails order3_p1 = new OrderDetails(order3, pPhone, 1);
        OrderDetails order4_p1 = new OrderDetails(order4, oPhone, 2);
        OrderDetails order4_p2 = new OrderDetails(order4, pPhone, 1);
        OrderDetails order4_p3 = new OrderDetails(order4, kPhone, 1);
        OrderDetails order5_p1 = new OrderDetails(order5, kPhone2, 1);
        orderDetailsRepo.saveAll(Arrays.asList(order1_p1, order1_p2, order2_p1,
                order2_p2, order3_p1, order4_p1, order4_p2, order4_p3, order5_p1));

        // Set OrderDetails for each Order
        order1.setOrderDetails(Arrays.asList(order1_p1, order1_p2));
        order2.setOrderDetails(Arrays.asList(order2_p1, order2_p2));
        order3.setOrderDetails(List.of(order3_p1));
        order4.setOrderDetails(Arrays.asList(order4_p1, order4_p2, order4_p3));
        order5.setOrderDetails(List.of(order5_p1));
        orderRepo.saveAll(Arrays.asList(order1, order2, order3, order4, order5));

        // Create Payment Data -- amended
        PaymentRecord pay1 = new PaymentRecord(order1, "2024-10-01", "Completed", "93DJ2231ADD35672D");
        PaymentRecord pay2 = new PaymentRecord(order2, "2024-10-03", "Processing", "21EP5560BCV89263P");
        PaymentRecord pay3 = new PaymentRecord(order3, "2024-10-02", "Completed", "77TY3198JKS15629Q");
        PaymentRecord pay4 = new PaymentRecord(order4, "2024-10-05", "Unsuccessful", "09ZC4667GBM48204Y");
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
                "Singapore", "233020", "Completed");
        Shipping ship2 = new Shipping(order2, "3 Robinson Ring",
                "Singapore", "342503", "Completed");
        Shipping ship3 = new Shipping(order3, "25 Heng Mui Keng Terrace",
                "Singapore", "119617", "Processing");
        Shipping ship4 = new Shipping(order4, "21 Lower Kent Ridge Rd",
                "Singapore", "119077", "Processing");
        shippingRepo.saveAll(Arrays.asList(ship1, ship2, ship3, ship4));

        // Update Shipping / Order Status and Shipping Date
        ship1.setShippingStatus("Shipped");
        ship1.setShippingDate("2024-10-02");
        ship3.setShippingStatus("Shipped");
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
