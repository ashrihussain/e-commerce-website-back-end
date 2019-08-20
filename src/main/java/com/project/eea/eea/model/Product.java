package com.project.eea.eea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product", schema="eea")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer pid;
    @Column(name="product_name")
    private String productName;
    @Column(name="quantity")
    private String quantity;
    @Column(name="price")
    private String price;

    @Column(name="prod_image")
    private String prodImage;
    @Column(name="status")
    private String status;
    @Column(name="prod_desc")
    private String prod_desc;

    @OneToMany(mappedBy = "product")
//    @JoinColumn(name = "orderitem_id")
    private Set<OrderItem> pOrderItems;

//    @ManyToMany(mappedBy = "products")
//    private Set<Favourite> favourites;

    @JsonIgnore
    @OneToMany(mappedBy="prod_id")
    private Set<Review> reviews;


    @Column(name="prod_hsimage")
    private String prod_hsimage;

    public Product() {
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Product(String productName, String quantity, String price, String prodImage, String status) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.prodImage = prodImage;
        this.status = status;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getProd_hsimage() {
        return prod_hsimage;
    }

    public void setProd_hsimage(String prod_hsimage) {
        this.prod_hsimage = prod_hsimage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
