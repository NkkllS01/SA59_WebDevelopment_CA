package sg.edu.nus.ophone.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//code by Team3.Kuo Chi
@Entity
@Table(name="payment_method")
public class PaymentMethod {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="method_name")
	private String methodName;

	private String description;
	
	@OneToMany(mappedBy="paymentMethod")
	private List<Payment> paymentMethod;
	
	public PaymentMethod() {}
	
	public PaymentMethod(String methodName, String description) {
		this.methodName = methodName;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Payment Method ID: " + id + ", Method Name: " + methodName + ", Description: " + description;
	}
	
}
