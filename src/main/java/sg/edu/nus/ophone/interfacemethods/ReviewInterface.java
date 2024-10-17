package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Review;

import java.util.List;

public interface ReviewInterface {
    void createNewReview(Review review);

    List<Review> SearchReviewByProductId(Long pid);

    Double GetAverageRatingByPid(Long pid);

    public List<Double> GetAverageRatingByKeyword(String keyword);
}
