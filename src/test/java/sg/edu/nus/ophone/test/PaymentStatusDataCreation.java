package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.PaymentStatus;
import sg.edu.nus.ophone.repository.PaymentStatusRepository;

@SpringBootTest
public class PaymentStatusDataCreation {
	@Autowired
	private PaymentStatusRepository statRepo;
	
	@Test
	void conTextLoad() {
	PaymentStatus stat1 = new PaymentStatus("Pending Payment","Pending customer to confirm payment information");
	PaymentStatus stat2 = new PaymentStatus("Validating Payment","Validating payment with 3rd party payment gateway");
	PaymentStatus stat3 = new PaymentStatus("Payment Complete","Payment has been accepted and transaction is complete");
	
	statRepo.save(stat1);
	statRepo.save(stat2);
	statRepo.save(stat3);
	}
}
