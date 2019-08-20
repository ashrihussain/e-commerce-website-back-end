package com.project.eea.eea.controller;

import com.project.eea.eea.services.UserService;
import com.project.eea.eea.model.User;
import com.project.eea.eea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

//    @RequestMapping("/login/{id}")
//    public Optional<User> getUserId(@PathVariable Integer id)
//    {
//        return userService.getUserById(id);
//    }

    @RequestMapping("/login/{username}")
    public User getUsername(@PathVariable String username)
    {
        return userService.getUserByUsername(username);
    }


    @RequestMapping(method= RequestMethod.POST, value="/login")
    public void addUser(@RequestBody User user) {
        userRepo.save(user);
    }

    @PutMapping("/users/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user ) {
        User newUser = userRepo.findByUsername(username);
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        userRepo.save(newUser);

    }


}
