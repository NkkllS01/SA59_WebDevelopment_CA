package sg.edu.nus.ophone.test;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Brand;
import sg.edu.nus.ophone.model.Product;
import sg.edu.nus.ophone.repository.BrandRepository;
import sg.edu.nus.ophone.repository.ProductRepository;

//code by Team3.Ng Jiamin
@SpringBootTest
public class BrandAndProductDataCreation {
  @Autowired
  private BrandRepository brandRepo;

  @Autowired
  private ProductRepository productRepo;

  @Test
  void contTextLoad() {
    Brand orange = new Brand("Orange", "Orange");
    Brand pineapple = new Brand("Pineapple", "Pineapple");
    Brand kiwi = new Brand("Kiwi", "Kiwi");

    brandRepo.saveAll(Arrays.asList(orange, pineapple, kiwi));

    Product ophone = new Product("Orange oPhone 24", "oPhone 24", 1500.00, 10, orange);
    Product pphone = new Product("Pineapple pPhone Pro", "pPhone Pro", 1900.00, 5, pineapple);
    Product kphone = new Product("Kiwi kPhone S3", "kPhone S3", 2024, 8, kiwi);

    productRepo.saveAll(Arrays.asList(ophone, pphone, kphone));
  }
}
