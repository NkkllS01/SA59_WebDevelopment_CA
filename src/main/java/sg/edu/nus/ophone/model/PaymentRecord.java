package sg.edu.nus.ophone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//code by Team3.Kuo Chi
@Entity
@Table(name="payment_record")
public class PaymentRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;

	@Column(name="payment_date")
	private LocalDateTime paymentDate;

	@Column(name="payment_amount")
	private double paymentAmount;

	@Column(name="status")
	private String status;

	@Column(name="paypal_id")
	private String paypalId;

	// constructors
	public PaymentRecord() {}

	public PaymentRecord(Order order, String paymentDate, String status, String paypalId) {
		this.order = order;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.paymentDate = LocalDateTime.parse(paymentDate, formatter);
		this.paymentAmount = order.getTotalAmount();
		this.status = status;
		this.paypalId = paypalId;
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

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = LocalDateTime.parse(paymentDate);
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	@Override
	public String toString() {
		return "Payment Record ID: " + id + ", Order: " + order + ", Payment Date: " + paymentDate +
				", Payment Amount: " + paymentAmount +
				", Status: " + status + ", PayPal ID: " + paypalId;
	}

}
