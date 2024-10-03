package sg.edu.nus.ophone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//code by Team3.Ng Jiamin
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;
  @Column(name = "unit_price")
  private double unitPrice;
  private int stock;
  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;
  private String imagePathName;

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

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(int unitPrice) {
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

  @Override
  public String toString() {
    return "Product ID: " + id + ", Name: " + name + ", Description: " + description + ", Unit Price: " + unitPrice
        + ", Stock: " + stock + ", Brand: " + brand;
  }

}
