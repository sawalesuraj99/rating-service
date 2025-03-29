package com.rating.app.controller;

import com.rating.app.entity.Rating;
import com.rating.app.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Create a new rating
    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    // Get all ratings
    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // Get a rating by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Long id) {
        Optional<Rating> rating = ratingService.getRatingById(id);
        return rating.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a rating
    @PutMapping("/update/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") Long id, @RequestBody Rating updatedRating) {
        try {
            Rating rating = ratingService.updateRating(id, updatedRating);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a rating by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRatingById(@PathVariable("id") Long id) {
        ratingService.deleteRatingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get ratings with a specific value
    @GetMapping("/filter/{value}")
    public ResponseEntity<List<Rating>> getRatingsByValue(@PathVariable("value") int value) {
        List<Rating> filteredRatings = ratingService.getAllRatings()
                .stream()
                .filter(rating -> rating.getValue() == value)
                .toList();
        return new ResponseEntity<>(filteredRatings, HttpStatus.OK);
    }
    
    @GetMapping("/user-{userId}/product-{productId}")
    public Rating getRatingByUserIdAndProductId(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        return ratingService.getRatingByUserIdAndProductId(userId, productId);
    }

    @GetMapping("/user-{userId}")
    public List<Rating> getRatingByUserId(@PathVariable("userId") Long userId) {
        return ratingService.getRatingByUserId(userId);
    }

    @GetMapping("/product-{productId}")
    public ResponseEntity<List<Rating>>  getRatingByProductId(@PathVariable("productId")Long productId) {
        return new ResponseEntity<>(ratingService.getRatingByProductId(productId),HttpStatus.OK);
    }
}
