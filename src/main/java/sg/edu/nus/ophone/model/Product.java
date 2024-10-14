package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

import java.util.List;

//code by Team3.Ng Jiamin
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String description;
  @Column(name = "stockQuantity")
  private int stock;
  private String imagePathName;

  @Column(name = "unit_price")
  private double unitPrice;

  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<Review> reviews;
  
 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetails;

  public Product() {
  }

  public Product(String name, String description, double unitPrice, int stock, Brand brand) {
    this.name = name;
    this.description = description;
    this.unitPrice = unitPrice;
    this.stock = stock;
    this.brand = brand;
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

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getImagePathName() {
    return imagePathName;
  }

  public void setImagePathName(String imagePathName) {
    this.imagePathName = imagePathName;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }



  @Override
  public String toString() {
    return "Product [id = " + id + ", name = " + name + ", description = " + description +
            ", unitPrice = " + unitPrice + ", stock = " + stock + "]";
  }
}
