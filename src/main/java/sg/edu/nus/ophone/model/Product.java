package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

import java.util.List;

//code by Team3.Ng Jiamin
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String description;
  @Column(name = "unit_price")
  private double unitPrice;
  private int stock;
  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;
  private String imagePathName;
  @OneToMany (mappedBy = "product")
  private List<Review> reviews;

  public Product() {
  }

  public Product(String name, String description, double unitPrice, int stock, Brand brand) {
    this.name = name;
    this.description = description;
    this.unitPrice = unitPrice;
    this.stock = stock;
    this.brand = brand;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }

  public String getImagePathName() {
    return imagePathName;
  }

  public void setImagePathName(String imagePathName) {
    this.imagePathName = imagePathName;
  }

  public List<Review> getReviews() {return reviews;}

  public void setReviews(List<Review> reviews) {this.reviews = reviews;}

  public double calculateRating() {
    if (reviews != null && !reviews.isEmpty()) {
      return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
    return 0.0;
  }

  @Override
  public String toString() {
    return "Product ID: " + id + ", Name: " + name + ", Description: " + description + ", Unit Price: " + unitPrice
        + ", Stock: " + stock + ", Brand: " + brand;
  }

}
