package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Product;

/**
 * 
 * Creating, RetriveProduct, and deleteProduct order details in the cart system.
 * 
 * Created by: LianDa,GaoZijie
 * Created on: 10/09/2024
 */

public interface ProductService {
	
    Product createProduct(Product product);
    
    Product getProductById(Long productId);
    
    void deleteProduct(Long productId);
}