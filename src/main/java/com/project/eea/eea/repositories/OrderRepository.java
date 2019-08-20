package com.project.eea.eea.repositories;

import com.project.eea.eea.model.Order;
import com.project.eea.eea.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface    OrderRepository extends CrudRepository<Order, String> {

    List<Order> findAll();
    List<Order> findByStatus(String status);
    List<Order> findByUser(User user);
    Order findTopByOrderByIdDesc();



    @Query("SELECT o FROM Order o WHERE o.status = 'purchased' AND  o.user.username = :#{#user.username}")
    List<Order> findPurchasedByUser(@Param("user") User user);

    @Query("Select o.orderItems From Order o Where o.user.username= :#{#user.username}")
    List<Order> getOrderItemsOfOrderByUser(@Param("user") User user);

    @Query("Select o From Order o Where o.user.username= :#{#user.username} AND o.status = 'cart'")
    List<Order> getOrderItemsOfOrderByUserAndByStatus(@Param("user") User user);


    @Query("SELECT o FROM Order o WHERE o.status = 'temp' AND  o.user.username = :#{#user.username}")
    List<Order> findTempByUser(@Param("user") User user);

//    @Query("Select o From Order o Where o.user.username= :#{#user.username}")
//    Order getOrderbyOrder(@Param("user") User user);

}
