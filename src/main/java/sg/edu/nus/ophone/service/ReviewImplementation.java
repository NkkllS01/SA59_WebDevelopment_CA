package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.ReviewInterface;
import sg.edu.nus.ophone.model.Review;
import sg.edu.nus.ophone.repository.ReviewRepository;

@Service
@Transactional
public class ReviewImplementation implements ReviewInterface {
    @Autowired
    ReviewRepository reviewRepo;

    @Override
    public void createNewReview(Review review) {
        reviewRepo.save(review);
    }
}
