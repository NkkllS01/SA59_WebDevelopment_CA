package sg.edu.nus.ophone.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

//code by Team3.Gao Zijie
@Entity
public class Shipping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String address;
  private String city;
  private String postalCode;

  @Column(name = "shipping_date")
  private LocalDate shippingDate;

  @Column(name = "delivery_date")
  private LocalDate deliveryDate;

  //  @ManyToOne
  private String shippingStatus;

  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public Shipping() {
  }

  public Shipping(Order order, String address, String city, String postalCode) {
    this.order = order;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.shippingStatus = "Processing";
  }

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public LocalDate getShippingDate() {
    return shippingDate;
  }

  public void setShippingDate(String shippingDate) {
    this.shippingDate = LocalDate.parse(shippingDate);
  }

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = LocalDate.parse(deliveryDate);
  }

  public String getShippingStatus() {
    return shippingStatus;
  }

  public void setShippingStatus(String shippingStatus) {
    this.shippingStatus = shippingStatus;
  }

  @Override
  public String toString() {
    return "Shipping [id=" + id + ", address=" + address + ", shippingDate=" + shippingDate + ", deliveryDate="
            + deliveryDate + "]";
  }
}


