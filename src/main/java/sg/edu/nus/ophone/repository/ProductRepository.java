package sg.edu.nus.ophone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Product;

import java.util.List;

//code by Team3.Ng Jiamin
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("Select p from Product p where lower(p.name) like lower(CONCAT('%',:keyword,'%')) " +
            "or lower(p.brand.name) like lower(CONCAT('%',:keyword,'%'))")
    List<Product> searchProductByKey(@Param("keyword") String keyword);

    @Query("select p from Product p where p.id = :id")
    Product findProductById(@Param("id") long id);

    @Query("Select p from Product p")
    Page<Product> getProduct(Pageable pageable);
}
