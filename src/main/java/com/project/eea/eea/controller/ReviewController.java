package com.project.eea.eea.controller;

import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.Review;
import com.project.eea.eea.model.User;
import com.project.eea.eea.repositories.ProductRepository;
import com.project.eea.eea.repositories.ReviewRepository;
import com.project.eea.eea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/reviews")
    public List<Review> getReviews()
    {
        return reviewRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST, value="/reviews")
    @ResponseBody
    public void addReview(@RequestBody Review review) {
        Review newReview = new Review();

        Product p = productRepository.findByPid(review.getProd_id().getPid());
        User u = userRepository.findByUsername(review.getUser().getUsername());


        newReview.setDate(review.getDate());
        newReview.setProd_id(p);
        newReview.setUser(u);
        newReview.setReview(review.getReview());
        reviewRepository.save(newReview);
    }

    @RequestMapping("/reviews/{pid}")
    public List<Review> getReview(@PathVariable int pid)
    {
        Product p = productRepository.findByPid(pid);
        return reviewRepository.findReviewsByProduct(p);
    }

}