package com.project.eea.eea.repositories;

import com.project.eea.eea.model.Product;
import com.project.eea.eea.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, String> {

    List<Review> findAll();


    @Query("SELECT r FROM Review r WHERE r.prod_id.pid = :#{#product.pid}")
    List<Review> findReviewsByProduct(@Param("product") Product product);
}
