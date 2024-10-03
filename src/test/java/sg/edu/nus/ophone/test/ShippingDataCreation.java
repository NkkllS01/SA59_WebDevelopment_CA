package sg.edu.nus.ophone.test;

package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Shipping;
import sg.edu.nus.ophone.repository.ShippingRepository;

//code by Team3.Gao Zijie
@SpringBootTest
public class ShippingDataCreation {
 @Autowired
 private ShippingRepository shiRepo;

 @Test
 void conTextLoad() {
 Shipping shi1 = new Shipping(1,"SG","2024-09-16","2024-09-30",3,133);
 Shipping shi2 = new Shipping(2,"CN","2024-09-11","2024-09-23",2,132);
 Shipping shi3 = new Shipping(3,"MY","2024-09-03","2024-09-11",3,144);
 Shipping shi4 = new Shipping(4,"IND","2024-06-13","2024-09-22",3,155);

 shiRepo.save(shi1);
 shiRepo.save(shi2);
 shiRepo.save(shi3);
 shiRepo.save(shi4);
 }
}
