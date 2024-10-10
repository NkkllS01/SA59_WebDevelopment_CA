package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.ProductInterface;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.repository.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductImplementation implements ProductInterface {
    @Autowired
    ProductRepository prepo;

    @Override
    public Product getProductById(Long productId) {
        return prepo.findProductById(productId);
    }

    @Override
    @Transactional
    public List<Product> searchProductByKey(String keyword) {
        return prepo.searchProductByKey(keyword);
    }

    @Override
    public Product searchProductById(Integer id) {
        return prepo.findProductById(id);
    }

    @Override
    @Transactional
    public List<Product> getProduct() {
        return prepo.getProduct();
    }

    @Override
    public Product createProduct(Product product) {
        return prepo.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        prepo.deleteById(productId);
    }
}
