package com.project.eea.eea.model;


import javax.persistence.*;

@Entity
@Table(name="review", schema="eea")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rid")
    private int rid;
    @Column(name="review")
    private String review;
    @Column(name="date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    Product prod_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Product getProd_id() {
        return prod_id;
    }

    public void setProd_id(Product prod_id) {
        this.prod_id = prod_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
