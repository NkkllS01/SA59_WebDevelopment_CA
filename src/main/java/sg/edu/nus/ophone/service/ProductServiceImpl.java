package sg.edu.nus.ophone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.ophone.interfacemethods.ProductService;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.repository.ProductRepository;


/**
 * 
 * Creating, RetriveProduct, and deleteProduct order details in the cart system.
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}