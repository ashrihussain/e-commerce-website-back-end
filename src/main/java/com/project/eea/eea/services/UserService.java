package com.project.eea.eea.services;

import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.User;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return   userRepository.findById(id);
    }
    public User getUserByUsername(String username) {
        return   userRepository.findByUsername(username);
    }
    public Product getProductById(Integer pid) {
        return   productRepository.findByPid(pid);
    }

}
