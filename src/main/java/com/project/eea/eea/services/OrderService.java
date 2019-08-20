package com.project.eea.eea.services;

import com.project.eea.eea.model.Order;
import com.project.eea.eea.model.OrderItem;
import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.User;
import com.project.eea.eea.repositories.OrderItemRepository;
import com.project.eea.eea.repositories.OrderRepository;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    public void saveOrder(Order order) {

        User user = userRepository.findByUsername(order.getUser().getUsername());

        Order newOrder = new Order();

        newOrder.setUser(user);
        newOrder.setStatus(order.getStatus());
        newOrder.setOrder_date(order.getOrder_date());
        newOrder.setComment(order.getComment());
        orderRepository.save(newOrder);

        Order o = orderRepository.findTopByOrderByIdDesc();


        for(OrderItem oi : order.getOrderItems()){
            oi.setOrder(o);
            oi.setStatus("purchased");
            orderItemRepository.save(oi);
        }

        o.setOrderItems(order.getOrderItems());
        orderRepository.save(o);

    }

    public void orderItemAdd(OrderItem orderItem) {

        OrderItem newOrderItem = new OrderItem();
        Product product = productRepository.findByPid(orderItem.getProduct().getPid());
        User user = userRepository.findByUsername(orderItem.getUser().getUsername());

        newOrderItem.setProduct(product);
        newOrderItem.setQuantity(orderItem.getQuantity());
        newOrderItem.setStatus(orderItem.getStatus());
        newOrderItem.setUser(user);

        orderItemRepository.save(newOrderItem);
    }

    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }

    public void deleteItemFromCart( int oiid, String username) {
        User u = userRepository.findByUsername(username);
        OrderItem oi = orderItemRepository.findById(oiid);
        OrderItem orderItem = orderItemRepository.findOrderItemByUserDelete(oi, u);
        orderItemRepository.delete(orderItem);

    }


}
