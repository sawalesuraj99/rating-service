package com.rating.app.repository;

import com.rating.app.entity.Rating;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	Optional<Rating> findByUserIdAndProductId(Long userId, Long productId);

	Optional<List<Rating>> findByUserId(Long userId);

	Optional<List<Rating>> findByProductId(Long productId);


    // You can define custom query methods if needed
    // For example:
    // List<Rating> findByValue(int value);
}

