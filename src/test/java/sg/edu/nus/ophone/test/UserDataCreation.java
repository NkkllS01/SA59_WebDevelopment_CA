package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.User;
import sg.edu.nus.ophone.repository.UserRepository;

import java.util.Arrays;

//code by Team3.Cynthia Peh
@SpringBootTest
public class UserDataCreation {

    @Autowired
    private UserRepository userRepo;

    @Test
    void contextLoads() {
        User user1 = new User("Customer", "Andy", "andy@email.com", "aa12345aa", "20 Holland Village Road 23300");
        User user2 = new User("Customer", "Billy", "billy@email.com", "3b3b8888", "3 Robinson Ring 34250");
        User user3 = new User("Staff", "Candy", "candy@email.com", "12345678Cc",
                "123 Jurong West #05-28 Singapore 66832");

        userRepo.saveAll(Arrays.asList(user1, user2, user3));
    }
}
