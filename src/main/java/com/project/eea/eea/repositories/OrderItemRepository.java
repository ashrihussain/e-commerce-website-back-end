package com.project.eea.eea.repositories;

import com.project.eea.eea.model.OrderItem;
import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {

    List<OrderItem> findAll();
    OrderItem findById(Integer id);
    List<OrderItem> findByStatus(String status);
    List<OrderItem> findOrderByUserAndProduct(User user, Product product);
    List<Product> findByProduct(Product product);
    @Transactional
    int deleteById(int id);

    @Query("SELECT o from OrderItem o WHERE o.id=:#{#orderItem.id} and o.user.id=:#{#user.id}")
    OrderItem findOrderItemByUserDelete(@Param("orderItem") OrderItem oi, @Param("user") User user);


    @Query("SELECT o.product  FROM OrderItem o WHERE o.status = 'cart' AND  o.user.username = :#{#user.username}")
    List<OrderItem> findCartByUser(@Param("user") User user);

    @Query("SELECT o  FROM OrderItem o WHERE o.status = 'cart' AND  o.user.username = :#{#user.username}")
        List<OrderItem> findOrderitemsByUser(@Param("user") User user);

    @Query("SELECT o  FROM OrderItem o WHERE o.status = 'Purchased' AND  o.user.username = :#{#user.username}")
    List<OrderItem> findPurcahsedOfUser(@Param("user") User user);

    @Query("SELECT o  FROM OrderItem o WHERE o.status = 'temp' AND  o.user.username = :#{#user.username}")
    List<OrderItem> findPurchasedItems(@Param("user") User user);

    @Query("SELECT o.product  FROM OrderItem o WHERE o.product.pid = :#{#product.pid}")
    List<OrderItem> findProductFromList(@Param("product") Product product);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE OrderItem o SET o.quantity = :#{#quantity} WHERE o.product.pid = :#{#product.pid} AND o.user.id= :#{#user.id}")
    void updateQuantity(@Param("product") Product product, @Param("quantity") int quantity, @Param("user") User user);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE OrderItem o SET o.status ='purchased' where o.status='cart' and o.user.id= :#{#user.id}")
    void updateStatus(@Param("user") User user);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE OrderItem o SET o.status ='' where o.id=:#{#id} AND o.user.id= :#{#user.id}")
    void updateStatusRemove(@Param("user") User user);

//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE OrderItem o SET o.quantity = :#{#quantity} WHERE o.product.pid = :#{#product.pid} AND o.user.username= :#{#user.username}")
//    void updateQuantity(@Param("product") Product product, @Param("quantity") int quantity, @Param("user") User user);
//
}
