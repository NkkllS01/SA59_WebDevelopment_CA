package sg.edu.nus.ophone.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//code by Team3.Gao Zijie
@Entity
public class ShippingStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;

//  @OneToMany(mappedBy = "ShippingStatus")
//  private List<Shipping> shippings;

  public ShippingStatus() {
  }

  public ShippingStatus(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ShippingStatus [id=" + id + ", name=" + name + ", description=" + description + "]";
  }

}
