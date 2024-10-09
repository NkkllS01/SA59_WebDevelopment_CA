package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Review;

import java.util.List;

//code by Team3.Song Jingze
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.rating > 3")
    List<Review> findAllReviews();

    @Query("select r from Review r where r.product.id = :id")
    List<Review> findReviewsByProductId(@Param("id") int id);

    @Query("select r from Review r where r.user.id = :id")
    List<Review> findReviewsByUserId(@Param("id") int id);
}
