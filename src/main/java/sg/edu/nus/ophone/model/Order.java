package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//code by Team3.Chen Sirui
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private OrderStatus orderstatus;

    @OneToOne
    @JoinColumn(name = "payment_id",referencedColumnName = "id")
    private Payment payment;

    @OneToMany (mappedBy = "order")
    private List<OrderDetails> orderDetails;

    // constructors
    public Order() {}
    public Order(int id, User user, String orderDate, double totalAmount) {
        this.id = id;
        this.user = user;
        this.orderDate = LocalDate.parse(orderDate);
        this.totalAmount = totalAmount;
    }

    // getters and setters
    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderstatus() {
        return orderstatus;
    }

    public void setOrderStatus(OrderStatus orderstatus) {
        this.orderstatus = orderstatus;
    }
}
