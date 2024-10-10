package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Review;

import java.util.List;

public interface ReviewInterface {
    public void createNewReview(Review review);

    public List<Review> SearchReviewByProductId(Integer pid);

    public Double GetAverageRating(Integer pid);
}
