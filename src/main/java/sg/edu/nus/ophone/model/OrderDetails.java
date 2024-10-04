package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

//code by Team3.Lian Da
@Entity
@IdClass(OrderDetailsId.class)
public class OrderDetails {

    @Id
    @JoinColumn(name = "order_id")
    private Long orderId;

    @Id
    @JoinColumn(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    private Order order;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(Long orderId, Long productId, int quantity, double unitPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

}
