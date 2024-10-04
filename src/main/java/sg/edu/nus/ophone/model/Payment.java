package sg.edu.nus.ophone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//code by Team3.Kuo Chi
@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="payment_date")
	private String paymentDate;
	
	@Column(name="payment_amount")
	private double paymentAmount;
	
	@Column(name="method_id")
	private int methodId;
	
	@Column(name="status")
	private String status;
	
	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order Order;
	
	@ManyToOne
	@JoinColumn(name="method_id", referencedColumnName="id")
	private PaymentMethod paymentMethod;

	public Payment() {}
	
	public Payment(int orderId, String paymentDate, double paymentAmount, int methodId, String status) {
		this.orderId = orderId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.methodId = methodId;
		this.status = status;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getOrderId() {

		return orderId;
	}

	public void setOrderId(int orderId) {

		this.orderId = orderId;
	}

	public String getPaymentDate() {

		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {

		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {

		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {

		this.paymentAmount = paymentAmount;
	}

	public int getMethodId() {

		return methodId;
	}

	public void setMethodId(int methodId) {

		this.methodId = methodId;
	}

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Payment ID: " + id + ", Order ID: " + orderId + ", Payment Date: " + paymentDate + 
				", Payment Amount: " + paymentAmount + ", Method ID: " + methodId + 
				", Status: " + status;
	}

}
