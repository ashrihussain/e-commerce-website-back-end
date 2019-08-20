package com.project.eea.eea.services;


import com.project.eea.eea.model.OrderItem;
import com.project.eea.eea.model.Product;
import com.project.eea.eea.repositories.OrderItemRepository;
import com.project.eea.eea.repositories.OrderRepository;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    public void updateProduct(int prodId, Product product ) {

        Product mutateProduct = productRepository.findByPid(prodId);
        mutateProduct.setPrice(product.getPrice());
        mutateProduct.setQuantity(product.getQuantity());
        productRepository.save(mutateProduct);

    }

    public void updateProductStock( OrderItemWrapper orderItemWrapper ) {

        List<Product> products = new ArrayList<>();

        for (OrderItem orderItem : orderItemWrapper.getOrderItemList()) {

            Product product = productRepository.findByPid(orderItem.getProduct().getPid());

            int oldQuantity = Integer.parseInt(product.getQuantity());
            int newQuantity = orderItem.getQuantity();
            int updatedQuantity = oldQuantity - newQuantity;

            product.setPrice(orderItem.getProduct().getPrice());
            product.setProdImage(orderItem.getProduct().getProdImage());

            product.setProductName(orderItem.getProduct().getProductName());
            product.setStatus(orderItem.getProduct().getStatus());
            product.setProd_desc(orderItem.getProduct().getProd_desc());
            String sUpdatedQuantity = String.valueOf(updatedQuantity);

            product.setQuantity(sUpdatedQuantity);

            productRepository.save(product);

        }
    }
}
