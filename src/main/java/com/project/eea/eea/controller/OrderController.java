package com.project.eea.eea.controller;


import com.project.eea.eea.model.Order;
import com.project.eea.eea.model.OrderItem;
import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.User;
import com.project.eea.eea.repositories.OrderItemRepository;
import com.project.eea.eea.repositories.OrderRepository;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.repositories.UserRepository;
import com.project.eea.eea.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    OrderService orderService;

    @RequestMapping("/orders")
    public void getAllOrders() {
        orderService.getAllOrders();
    }

    @RequestMapping("/oitems")
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @RequestMapping("/orderItems/cart")
    public List<Order> getAllCartItems() {
        return orderRepository.findByStatus("cart");
    }

    @RequestMapping("/orders/purchased")
    public List<Order> getAllPurchasedItems() {
        return orderRepository.findByStatus("purchased");
    }


    @RequestMapping("/orders/{username}")
    public List<Order> getOrdersByUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findByUser(user);
    }




    @RequestMapping("/orders/orderitems/{username}")
    public List<OrderItem> getOrderitemsWithUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findPurcahsedOfUser(user);
    }

    @RequestMapping("/orders/orderitems/cart/{username}")
    public List<OrderItem> getOrderitemsWithUserandStatus(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findOrderitemsByUser(user);
    }


    @RequestMapping("/orders/purchased/{username}")
    public List<Order> getPurchasedItemsWithUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findPurchasedByUser(user);
    }

    @RequestMapping("/orders/temp/{username}")
    public List<Order> getOrdertempwithUser(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderRepository.findTempByUser(user);
    }

    @RequestMapping(method= RequestMethod.POST, value="/orders/{username}")
    @ResponseBody
    public void saveOrder(@RequestBody Order order) {

        orderService.saveOrder(order);

    }

    @RequestMapping("/orderItems/cart/orderItem/product/{username}")
    public List<OrderItem> getShoppingCartWithUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        return orderItemRepository.findCartByUser(user);
    }

    @RequestMapping("/orderItems/cart/orderItem/{username}")
    public List<OrderItem> getOrdersWithUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findOrderitemsByUser(user);
    }

    @RequestMapping(value = "/orderItems/{oiid}/{username}", method = RequestMethod.DELETE)
    public void deleteItemFromCart(@PathVariable("oiid") int oiid, @PathVariable("username") String username) {
        orderService.deleteItemFromCart(oiid,username);

    }

    @PutMapping("/orderItems/cart/orderItem/{username}/purchased")
    public void purcahseShoppingCartItems(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        orderItemRepository.updateStatus(user);

    }

    @RequestMapping("/orderItems/cart/orderItem/product/{username}/{prodId}")
    public List<OrderItem> getAllProductsFromShoppingCart(@PathVariable String username, @PathVariable String prodId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findByPid(Integer.parseInt(prodId));
        List<OrderItem> orderItems = orderItemRepository.findCartByUser(user);
        return orderItemRepository.findProductFromList(product);
    }

    @RequestMapping(method= RequestMethod.POST, value="/oitems")
    public void addNewOrderItem(@RequestBody OrderItem orderItem) {
        orderService.orderItemAdd(orderItem);
    }

    @PutMapping("/orderItems/cart/orderItem/{username}/{pid}/{quantity}")
    public void updateShoppingCart(@PathVariable("username") String username,  @PathVariable("pid") int pid, @PathVariable("quantity") int quantity ) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findByPid(pid);
        orderItemRepository.updateQuantity(product, quantity, user);
    }

    @RequestMapping("/orderItems/purchased/{username}")
    public List<OrderItem> getAllPurcahsed(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        return orderItemRepository.findPurcahsedOfUser(user);
    }

    @RequestMapping(value = "/orderItems/cart/{id}}", method = RequestMethod.DELETE)
    public void DeleteShoppingCartItem(@PathVariable int id) {
        OrderItem orderItem = orderItemRepository.findById(id);
        orderItemRepository.deleteById(id);
    }

}









