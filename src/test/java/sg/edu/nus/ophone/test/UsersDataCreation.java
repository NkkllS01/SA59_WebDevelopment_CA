package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Users;
import sg.edu.nus.ophone.repository.UsersRepository;

import java.util.Arrays;

//code by Team3.Cynthia Peh
@SpringBootTest
public class UsersDataCreation {

    @Autowired
    private UsersRepository usersRepo;

    @Test
    void contextLoads() {
        Users user1 = new Users("Customer", "Andy", "andy@email.com", "aa12345aa", "20 Holland Village Road 23300");
        Users user2 = new Users("Customer", "Billy", "billy@email.com", "3b3b8888", "3 Robinson Ring 34250");
        Users user3 = new Users("Staff", "Candy", "candy@email.com", "12345678Cc",
                "123 Jurong West #05-28 Singapore 66832");

        usersRepo.saveAll(Arrays.asList(user1, user2, user3));
    }
}
