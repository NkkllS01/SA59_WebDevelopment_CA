package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.Payment;
import sg.edu.nus.ophone.repository.PaymentRepository;

@SpringBootTest
public class PaymentDataCreation {
	@Autowired
	private PaymentRepository payRepo;
	
	@Test
	void conTextLoad() {
	Payment pay1 = new Payment(1,"2024-09-12",2500.00,3,"Completed");
	Payment pay2 = new Payment(2,"2024-09-16",1800.50,2,"Completed");
	Payment pay3 = new Payment(3,"2024-09-18",2189.32,1,"Validating");
	Payment pay4 = new Payment(4,"2024-09-22",2189.32,2,"Pending payment details");
	
	payRepo.save(pay1);
	payRepo.save(pay2);
	payRepo.save(pay3);
	payRepo.save(pay4);
	}
}
