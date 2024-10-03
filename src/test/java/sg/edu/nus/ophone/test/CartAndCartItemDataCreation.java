package sg.edu.nus.ophone;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Cart;
import sg.edu.nus.ophone.model.CartItem;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.model.Users;
import sg.edu.nus.ophone.repository.CartRepository;
import sg.edu.nus.ophone.repository.CartItemRepository;
import sg.edu.nus.ophone.repository.ProductRepository;
import sg.edu.nus.ophone.repository.UsersRepository;

@SpringBootTest
public class CartAndCartItemDataCreation {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Test
    void contTextLoad() {
        // 创建测试用户
        Users user1 = new Users("John Doe", "johndoe@example.com");
        Users user2 = new Users("Jane Smith", "janesmith@example.com");
        userRepo.saveAll(Arrays.asList(user1, user2));

        // 创建测试产品
        Product product1 = new Product("Phone A", "Smartphone", 1200.00, 20, "Supplier A");
        Product product2 = new Product("Phone B", "Smartphone", 1500.00, 15, "Supplier B");
        productRepo.saveAll(Arrays.asList(product1, product2));

        // 创建购物车并关联用户
        Cart cart1 = new Cart(user1);
        Cart cart2 = new Cart(user2);
        cartRepo.saveAll(Arrays.asList(cart1, cart2));

        // 创建购物车项并关联购物车和产品
        CartItem cartItem1 = new CartItem(cart1, product1, 2); // 用户1的购物车，2件Product1
        CartItem cartItem2 = new CartItem(cart1, product2, 1); // 用户1的购物车，1件Product2
        CartItem cartItem3 = new CartItem(cart2, product1, 3); // 用户2的购物车，3件Product1

        cartItemRepo.saveAll(Arrays.asList(cartItem1, cartItem2, cartItem3));
    }
}
