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
        Users user1 = new Users("Customer", "Andy", "andy@email.com", "20 Holland Village Road", "Singapore", "23300", "aa12345aa");
        Users user2 = new Users("Customer", "Billy", "billy@email.com", "3 Robinson Ring", "Singapore", "34250", "3b3b8888");
        Users user3 = new Users("Staff", "Candy", "candy@email.com", "123 Jurong West #05-28", "Singapore", "66832", "12345678Cc");

        usersRepo.saveAll(Arrays.asList(user1, user2, user3));
    }
}
