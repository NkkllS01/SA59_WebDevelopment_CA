package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Product;

import java.util.List;

//code by Team3.Ng Jiamin
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.name like %:keyword%")
    List<Product> findProductsByKeyword(@Param("keyword") String keyword);

    @Query("select p from Product p where p.id = :id")
    Product findProductById(@Param("id") int id);
}
