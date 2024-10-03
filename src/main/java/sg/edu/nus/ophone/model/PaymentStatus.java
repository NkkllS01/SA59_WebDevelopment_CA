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
@Table(name="payment_status")
public class PaymentStatus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "status_name")
	private String statusName;
	private String description;
	
	@OneToMany(mappedBy="paymentStatus")
	private List<Payment> paymentStatus;
	
	public PaymentStatus() {}
	
	public PaymentStatus(String statusName, String description) {
		this.statusName = statusName;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return statusName;
	}

	public void setStatus(String statusName) {
		this.statusName = statusName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Payment Status ID: " + id + ", Status Name: " + statusName + ", Description: " + description;
	}
	
}
