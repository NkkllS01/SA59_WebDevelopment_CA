package sg.edu.nus.ophone.model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//code by Team3.Chen Sirui
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    //    @ManyToOne
//    @JoinColumn(name = "status", referencedColumnName = "id")
    private String orderStatus;

    @OneToOne(mappedBy = "order")
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentRecord paymentRecord;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    @OneToOne(mappedBy = "order")
    private Shipping shipping;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    // constructors
    public Order() {
    }

    public Order(User user, String orderDate) {
        this.user = user;
        this.orderDate = LocalDate.parse(orderDate);
        this.orderStatus = "Cart";
    }
    
	public void createOrder() {
        if ("cart".equals(this.orderStatus)) {
            this.orderStatus = "order";
        }
        
    }

    public void cancelOrder() {
       
    }

    // getters and setters
    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails != null ? orderDetails : new ArrayList<>();
        if (orderDetails != null && !orderDetails.isEmpty()) {
            this.totalAmount = orderDetails.stream().map(OrderDetails::getAmount).reduce(0.0, Double::sum);
        } else {
            this.totalAmount = 0.0;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentRecord getPayment() {
        return paymentRecord;
    }

    public void setPayment(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public void setPaymentStatus(String paymentStatus) {
        if (this.paymentRecord != null) {
            this.paymentRecord.setStatus(paymentStatus);
        }
    }
    }


