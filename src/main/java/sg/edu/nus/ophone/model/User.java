package sg.edu.nus.ophone.model;

import java.util.List;

import jakarta.persistence.*;

//code by Team3.Cynthia Peh
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(length = 30)
  private String userType;

  @Column(length = 50)
  private String name;

  @Column(length = 100)
  private String email;
  private String address;
  private String city;
  private String postalCode;

  @Column(length = 20)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;

  @OneToOne(mappedBy = "user")
  private Cart cart;

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

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }


}
