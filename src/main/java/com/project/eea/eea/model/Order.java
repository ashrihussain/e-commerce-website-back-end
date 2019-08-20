package com.project.eea.eea.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="orders", schema="eea")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer id;
    @Column(name="order_date")
    private String order_date;
    @Column(name="status")
    private String status;
    @Column(name="comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @OneToMany(mappedBy="order",cascade = {CascadeType.PERSIST  })
    private Set<OrderItem> orderItems;


    public Order() {
    }

    public Order(String order_date, String status, String comment, User user, Set<OrderItem> orderItems) {
        this.order_date = order_date;
        this.status = status;
        this.comment = comment;
        this.user = user;
        this.orderItems = orderItems;
    }



    public Order(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
