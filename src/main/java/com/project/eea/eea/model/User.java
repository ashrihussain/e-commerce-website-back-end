package com.project.eea.eea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user", schema="eea")
public class    User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;
    @Column(name="user_name")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email_address")
    private String emailAddress;
    @Column(name="name")
    private String firstName;
    @Column(name="home_address")
    private String residentAddress;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name="status")
    private String status;
    @Column(name="profileimg")
    private String profileimg;
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<OrderItem> orderItems;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Review> reviews;


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public User() {
    }

    public User(String username, String password, String emailAddress, String firstName, String residentAddress, String contactNumber) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.residentAddress = residentAddress;
        this.contactNumber = contactNumber;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
