package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.ShippingStatus;
import sg.edu.nus.ophone.repository.ShippingStatusRepository;

//code by Team3.Gao Zijie
@SpringBootTest
public class ShippingStatusDataCreation {
 @Autowired
 private ShippingStatusRepository shippingstuRepo;

 @Test
 void conTextLoad() {
 ShippingStatus shippingstu1 = new ShippingStatus(1,"Pending","Order has been created and is waiting for shipment.");
 ShippingStatus shippingstu2 = new ShippingStatus(2,"Shipped","Order has been shipped and is on the way.");
 ShippingStatus shippingstu3 = new ShippingStatus(3,"Out for Delivery","Order has arrived at the local delivery center and is out for delivery.");
 ShippingStatus shippingstu4 = new ShippingStatus(4,"Delivered","Order has been successfully delivered to the customer.");

 shippingstuRepo.save(shippingstu1);
 shippingstuRepo.save(shippingstu2);
 shippingstuRepo.save(shippingstu3);
 shippingstuRepo.save(shippingstu4);
 }
}
