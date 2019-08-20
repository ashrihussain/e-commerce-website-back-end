package com.project.eea.eea.controller;

import com.project.eea.eea.services.OrderItemWrapper;
import com.project.eea.eea.model.Product;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST, value="/products")
    @ResponseBody
    public void addNewProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @RequestMapping("/products/{productId}")
    public Product getSingleProduct(@PathVariable Integer productId)
    {
        return productRepository.findByPid(productId);
    }

    @PutMapping("/products/{prodId}")
    public void updateProduct(@PathVariable int prodId, @RequestBody Product product ) {
        productService.updateProduct(prodId, product);
    }

    @PutMapping("/products")
    public void updateProductStock(@RequestBody OrderItemWrapper orderItemWrapper ) {

        productService.updateProductStock(orderItemWrapper);

    }

}
