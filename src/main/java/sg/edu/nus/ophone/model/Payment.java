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

	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;

	@Column(name="payment_date")
	private String paymentDate;
	
	@Column(name="payment_amount")
	private double paymentAmount;
	
	@Column(name="status")
	private String status;

	@ManyToOne
	@JoinColumn(name="method_id", referencedColumnName="id")
	private PaymentMethod paymentMethod;

	// constructors
	public Payment() {}
	public Payment(Order order, String paymentDate, PaymentMethod paymentMethod, String status) {
		this.order = order;
		this.paymentDate = paymentDate;
		this.paymentAmount = order.getTotalAmount();
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	// getters & setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
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
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Payment ID: " + id + ", Order: " + order + ", Payment Date: " + paymentDate +
				", Payment Amount: " + paymentAmount + ", Method: " + paymentMethod +
				", Status: " + status;
	}

}
