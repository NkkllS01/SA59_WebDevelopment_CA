package sg.edu.nus.ophone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    @Transactional
    public Product findProductById(Integer id) {
        return productRepo.findById(Long.valueOf(id)).orElse(null);
    }

    @Transactional
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }
}