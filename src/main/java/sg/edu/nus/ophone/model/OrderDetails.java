package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

//code by Team3.Lian Da
@Entity
@IdClass(OrderDetailsId.class)
public class OrderDetails {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name="order_id", insertable = false, updatable = false)

    private Order order;
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double amount;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Product product, int quantity, double amount) {
        this.order = order;
        this.product = product;
        this.orderId = order.getId();
        this.productId = product.getId();
        this.quantity = quantity;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
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
}
