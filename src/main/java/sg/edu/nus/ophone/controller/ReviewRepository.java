package sg.edu.nus.ophone.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.ophone.model.Review;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT ROUND(AVG(r.rating), 1) FROM Review r WHERE r.product.id = :pid")
    Double getAverageRatingByProductId(@Param("pid") int pid);

    List<Review> findByProductIdOrderByRatingDesc(Integer pId);
}
