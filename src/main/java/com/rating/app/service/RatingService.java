package com.rating.app.service;

import com.rating.app.entity.Rating;
import java.util.List;
import java.util.Optional;

public interface RatingService {

    // Save a new rating
    Rating saveRating(Rating rating);

    // Retrieve all ratings
    List<Rating> getAllRatings();

    // Retrieve a rating by ID
    Optional<Rating> getRatingById(Long id);

    // Update an existing rating
    Rating updateRating(Long id, Rating updatedRating);

    // Delete a rating by ID
    void deleteRatingById(Long id);
    
    List<Rating> getRatingByUserId(Long userId);
    
    List<Rating> getRatingByProductId(Long productId);
 
 // Retrieve a rating by ID
	Rating getRatingByUserIdAndProductId(Long userId, Long productId);
}

