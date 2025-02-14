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
        Brand oppo = new Brand("Oppo", "Oppo");
        Brand onePlus = new Brand("OnePlus", "OnePlus");
        Brand honor = new Brand("HONOR", "HONOR");
        Brand realme = new Brand("realme", "realme");
        brandRepo.saveAll(Arrays.asList(orange, pineapple, kiwi, huawei, xiaomi, apple, oppo, onePlus, honor, realme));


        // Create Product Data
        Product oPhone = new Product("Orange oPhone 24", "Third-generation Snapdragon 8 mobile platform | Self-developed gaming chip Q1 |" +
                "Wanli light-chasing imaging system | Full-time four-zone cooling system | 1.5K 144Hz all-weather super-sensitive screen.",
                1500.00, 40, orange);
        oPhone.setImagePathName("/images/iQOO 12.jpg");
        Product pPhone = new Product("Pineapple pPhone Pro", "1-inch gimbal-level main camera | Blueprint imaging chip V3+ | Zeiss 200 million APO super telephoto.",
                1900.00, 30, pineapple);
        pPhone.setImagePathName("/images/vivo X100 Ultra.jpg");
        Product kPhone = new Product("Kiwi kPhone S3", "Second-generation Snapdragon 8 mobile platform｜Rampage Engine 3.0" +
                "｜Ice Cooling System｜2K Eye Protection Screen｜5000mAh Large Battery", 2024, 99, kiwi);
        kPhone.setImagePathName("/images/Redmi K70.jpg");
        Product kPhone2 = new Product("Kiwi kPhone S1 Max", "The 6.94-inch ultra-clear flexible OLED screen has a peak brightness of up to 2200 nits. " +
                "The picture is still clear and bright under strong light, delicate and moving, allowing you to immerse yourself in watching movies. " +
                "With the pixel super light-transmitting technology, it has lower power consumption and the good show never stops.",
                1999, 56, kiwi);
        kPhone2.setImagePathName("/images/HUAWEI Pocket2.jpg");
        Product hwMate = new Product("HUAWEI Mate60", "It has always been a classic and has always been innovative. " +
                "The ultimate central axis symmetry, a continuation of the past. Concentric design, breaking boundaries, exploring new aesthetics.",
                1600, 90, huawei);
        hwMate.setImagePathName("/images/HUAWEI Mate60.jpg");
        Product hwPura = new Product("HUAWEI Pura70",
                "Ultra Speed Snapshot, Ultra Lighting Macro Telephoto Camera, Super Durable Kunlun Glass, 100W Wired & 80W Wireless SuperCharge",
                1548, 37, huawei);
        hwPura.setImagePathName("/images/HUAWEI Pura70.jpg");
        Product xmFold = new Product("Xiaomi MIX Fold 4",
                "ltra-thin four-curved body｜Xiaomi Keel 2.0 hinge｜Full carbon fiber support structure｜Leica Optical All-Star Quad Camera｜" +
                        "Leica Optical Summilux lens｜UHD screen with consistent internal and external vision｜Third-generation Snapdragon 8 mobile platform｜" +
                        "5100mAh Xiaomi Jinshajiang battery | DOU1.47 days｜Xiaomi Surge 67W wired second charge | Xiaomi Surge 50W wireless second charge｜" +
                        "Two-way satellite communication｜IPX8 waterproof｜Xiaomi HyperOS AI efficiency master",
                1582.50, 34, xiaomi);
        xmFold.setImagePathName("/images/Xiaomi MIX Fold4.jpg");
        Product xmUltra = new Product("Xiaomi MIX Fold4",
                "Second-generation Snapdragon 8 mobile platform｜Xiaomi Pengpai OS｜Rage Engine 3.0｜Ice Cooling™ system｜" +
                        "Second-generation high-end 2K Chinese screen｜Light Hunter™ 800 sensor｜120W + 5000mAh",
                1699, 28, xiaomi);
        xmUltra.setImagePathName("/images/Xiaomi 14Ultra.jpg");

        Product product9 = new Product("Oppo Find N3", "Find N3", 1680.00,50, oppo);
        product9.setImagePathName("/images/Oppo Find N3 Flip.png");
        Product product10 = new Product("Oppo Find N3 Flip", "Find N3 Flip", 1880.00,50, oppo);
        product10.setImagePathName("/images/Oppo Find N3 Flip.png");
        Product product11 = new Product("Oppo Find N3 Pro", "Find N3 Pro", 2080.00,20, oppo);
        product11.setImagePathName("/images/Oppo Find X3 Pro.png");
        Product product12 = new Product("Oppo A3", "A3", 1380.00,80, oppo);
        product12.setImagePathName("/images/Oppo A3.png");
        Product product13 = new Product("Oppo A3 Pro", "A3 Pro", 1580.00,50, oppo);
        product13.setImagePathName("/images/Oppo A3 Pro.jpg");
        Product product14 = new Product("OnePlus 12 5G", "12 5G", 1088.00,100, onePlus);
        product14.setImagePathName("/images/OnePlus 12 5G.jpg");
        Product product15 = new Product("OnePlus 12R 5G", "12R 5G", 888.00,150, onePlus);
        product15.setImagePathName("/images/OnePlus 12R 5G.jpg");
        Product product16 = new Product("HONOR Magic6 Pro", "Magic6 Pro", 1399.00,100, honor);
        product16.setImagePathName("/images/HONOR Magic6 Pro.png");
        Product product17 = new Product("HONOR Magic6", "Magic6", 1199.00,150, honor);
        product17.setImagePathName("/images/HONOR Magic6 Lite.png");
        Product product18 = new Product("HONOR Magic V3", "Magic V3", 1999.00,40, honor);
        product18.setImagePathName("/images/HONOR Magic V3.png");
        Product product19 = new Product("HONOR 200 Lite", "200 Lite", 999.00,200, honor);
        product19.setImagePathName("/images/HONOR 200 Lite.png");
        Product product20 = new Product("realme 13+ 5G", "13+ 5G", 499.00,200, realme);
        product20.setImagePathName("/images/realme 13+ 5G.jpg");
        Product product21 = new Product("realme 13 Pro+ 5G", "13 Pro+ 5G", 699.00,150, realme);
        product21.setImagePathName("/images/realme 13 Pro+ 5G.jpg");
        Product product22 = new Product("realme GT 6T", "GT 6T", 699.00,150, realme);
        product22.setImagePathName("/images/realme GT 6T.webp");
        productRepo.saveAll(Arrays.asList(oPhone, pPhone, kPhone, kPhone2, hwMate, hwPura, xmFold, xmUltra, product9, product10, product11,
                product12, product13, product14, product15, product16, product17, product18, product19, product20, product21, product22));

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
        PaymentRecord pay1 = new PaymentRecord(order1, "2024-10-01 14:30:45", "Completed", "93DJ2231ADD35672D");
        PaymentRecord pay2 = new PaymentRecord(order2, "2024-10-03 11:15:39", "Processing", "21EP5560BCV89263P");
        PaymentRecord pay3 = new PaymentRecord(order3, "2024-10-02 18:09:24", "Completed", "77TY3198JKS15629Q");
        PaymentRecord pay4 = new PaymentRecord(order4, "2024-10-05 22:02:58", "Unsuccessful", "09ZC4667GBM48204Y");
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
                "Singapore", "342503", "Processing");
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
