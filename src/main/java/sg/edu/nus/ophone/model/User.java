package sg.edu.nus.ophone.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//code by Team3.Cynthia Peh
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(length = 30)
  private String userType;

  @Column(length = 50)
  @NotBlank(message= "Required field")
  private String name;

  @Column(length = 100)
  @NotBlank(message= "Required field")
  private String email;
  @NotBlank(message= "Required field")
  private String address;
  @NotBlank(message= "Required field")
  private String city;
  @NotBlank(message= "Required field")
  private String postalCode;

  @Column(length = 20)
  @NotBlank(message= "Required field")
  @Size(min = 8, max = 20, message = "Password must be between 8-20 characters")
  private String password;

  @Transient
  private String confirmPassword;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;

  public User() {
  }

  public User(String userType, String name, String email, String password, String address, String city, String postalCode) {
    this.userType = userType;
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }



  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
