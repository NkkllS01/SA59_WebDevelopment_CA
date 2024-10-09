package sg.edu.nus.ophone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

//code by Team3.Gao Zijie
@Entity
public class Shipping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "Please enter your address.")
  private String address;
  @NotBlank(message = "Please enter the city you reside in.")
  private String city;
  @NotBlank(message = "Please enter your postal code.")
  private String postalCode;

  @Column(name = "shipping_date")
  private String shippingDate;

  @Column(name = "delivery_date")
  private String deliveryDate;

  //  @ManyToOne
  private String shippingStatus;

  @OneToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public Shipping() {
  }

  public Shipping(Order order, String address, String city, String postalCode, String shippingStatus) {
    this.order = order;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.shippingStatus = shippingStatus;
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

  public String getShippingDate() {
    return shippingDate;
  }

  public void setShippingDate(String shippingDate) {
    this.shippingDate = shippingDate;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getShippingStatus() {
    return shippingStatus;
  }

  public void setShippingStatus(String shippingStatus) {
    this.shippingStatus = shippingStatus;
  }

  @Override
  public String toString() {
    return "Shipping [id=" + id + ", Address=" + address + ", Ship Out Date=" + shippingDate + ", Delivery Date="
            + deliveryDate + ", Shipping Status=" + shippingStatus + "]";
  }
}



