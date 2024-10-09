package sg.edu.nus.ophone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.ophone.interfacemethods.ProductInterface;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.repository.ProductRepository;

@Service
@Transactional
public class ProductImplementation implements ProductInterface {
    @Autowired
    ProductRepository productRepo;

    @Override
    public Product getProductById(Long productId) {
        return productRepo.findProductById(productId);
    }
}
