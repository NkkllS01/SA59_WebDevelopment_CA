package sg.edu.nus.ophone.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.ophone.model.PaymentMethod;
import sg.edu.nus.ophone.repository.PaymentMethodRepository;

@SpringBootTest
public class PaymentMethodDataCreation {
	@Autowired
	private PaymentMethodRepository methRepo;
	
	@Test
	void conTextLoad() {
	PaymentMethod meth1 = new PaymentMethod("Credit Card","Accept credit cards issued by Visa and Mastercard");
	PaymentMethod meth2 = new PaymentMethod("Paypal","An online payment system that acts as the intermediary between a payer and payee");
	PaymentMethod meth3 = new PaymentMethod("Apple Pay","Works as a mobile wallet that enables a one-click payment option on websites that accept it");
	
	methRepo.save(meth1);
	methRepo.save(meth2);
	methRepo.save(meth3);
	}
}
