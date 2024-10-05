package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

//code by Team3.Lian Da
@Entity
@IdClass(OrderDetailsId.class)
public class OrderDetails {

//    @Id
//    @JoinColumn(name = "order_id")
//    private Long orderId;
//
//    @Id
//    @JoinColumn(name = "product_id")
//    private Long productId;
    @Id
    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    private Order order;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double amount;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Product product, int quantity, double amount) {
//        this.orderId = orderId;
//        this.productId = productId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

}
