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
	Payment pay1 = new Payment("202410001", "2024-09-12",2500.00,"Completed", "93DJ2231ADD35672D");
	Payment pay2 = new Payment("202410002", "2024-09-16",1800.50,"Completed", "21EP5560BCV89263P");
	Payment pay3 = new Payment("202410003", "2024-09-18",2189.32,"Cancelled", "77TY3198JKS15629Q");
	Payment pay4 = new Payment("202410004", "2024-09-22",2189.32,"Validating", "09ZC4667GBM48204Y");
	
	payRepo.save(pay1);
	payRepo.save(pay2);
	payRepo.save(pay3);
	payRepo.save(pay4);
	}
}
