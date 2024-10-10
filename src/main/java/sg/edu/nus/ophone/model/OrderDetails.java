package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

import java.util.List;

//code by Team3.Lian Da
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    // Map to the Order entity and use @MapsId to link the orderId field
    @ManyToOne
//    @MapsId("orderId")
    @JoinColumn(name="order_id", insertable = false, updatable = false)
    private Order order;

    // Map to the Product entity and use @MapsId to link the productId field
    @ManyToOne
//    @MapsId("productId")
    @JoinColumn(name="product_id", insertable = false, updatable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double amount;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.orderId = order.getId();
        this.productId = product.getId();
        this.quantity = quantity;
        this.amount = product.getUnitPrice() * quantity;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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
