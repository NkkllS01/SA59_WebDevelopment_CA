package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.ReviewInterface;
import sg.edu.nus.ophone.model.Review;
import sg.edu.nus.ophone.repository.ReviewRepository;

import java.util.List;

@Service
@Transactional
public class ReviewImplementation implements ReviewInterface {
    @Autowired
    ReviewRepository rrepo;

    @Override
    public void createNewReview(Review review) {
        rrepo.save(review);
    }

    @Override
    public List<Review> SearchReviewByProductId(Integer pid) {
        return rrepo.findByProductIdOrderByRatingDesc(pid);
    }

    @Override
    public Double GetAverageRating(Integer pid) {
        return rrepo.getAverageRatingByProductId(pid);
    }
}
