package sg.edu.nus.ophone.interfacemethods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sg.edu.nus.ophone.model.Product;

import java.util.List;
/**
 *
 * Creating, RetriveProduct, and deleteProduct order details in the cart system.
 *
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */
public interface ProductInterface {
    public Product getProductById(Long productId);
    public List<Product> searchProductByKey(String keyword);
    public Product searchProductById(Long id);
    Page<Product> getProduct(Pageable pageable);
    Product createProduct(Product product);
    void deleteProduct(Long productId);
}
