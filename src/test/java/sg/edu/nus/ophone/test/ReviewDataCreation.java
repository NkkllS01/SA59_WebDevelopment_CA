package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Review;
import sg.edu.nus.ophone.repository.ReviewRepository;

import java.util.Arrays;

//code by Team3.Song Jingze
@SpringBootTest
public class ReviewDataCreation {
    @Autowired
    private ReviewRepository prepo;

    @Test
    void conTextLoad() {
        Review r1 = new Review(5, "Excellent product!", "2023-10-01 10:00:00");
        Review r2 = new Review(4, "Very good, but pricey.", "2023-10-02 11:00:00");
        Review r3 = new Review(3, "Average experience.", "2023-10-03 12:00:00");

        prepo.saveAll(Arrays.asList(r1, r2, r3));
    }

}
