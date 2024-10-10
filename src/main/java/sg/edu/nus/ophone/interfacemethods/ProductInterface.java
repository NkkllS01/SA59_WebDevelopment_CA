package sg.edu.nus.ophone.interfacemethods;

import sg.edu.nus.ophone.model.Product;

import java.util.List;

public interface ProductInterface {
    public Product getProductById(Long productId);
    public List<Product> searchProductByKey(String keyword);
    public Product searchProductById(Integer id);
    public List<Product> getProduct();
}
