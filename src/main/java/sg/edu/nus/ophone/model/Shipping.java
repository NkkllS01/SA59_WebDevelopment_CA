package sg.edu.nus.ophone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

//code by Team3.Gao Zijie
@Entity
public class Shipping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String address;

  @Column(name = "shipping_date")
  private String shippingDate;

  @Column(name = "delivery_date")
  private String deliveryDate;

  @ManyToOne
  private ShippingStatus ShippingStatus;

  @OneToOne
  private Order order;

  public Shipping() {
  };

  public Shipping(int id, String address, String shippingDate, String deliveryDate) {
    this.id = id;
    this.address = address;
    this.shippingDate = shippingDate;
    this.deliveryDate = deliveryDate;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  @Override
  public String toString() {
    return "Shipping [id=" + id + ", address=" + address + ", shippingDate=" + shippingDate + ", deliveryDate="
        + deliveryDate + "]";
  }

}
