package com.rating.app.service.impl;

import com.rating.app.entity.Rating;
import com.rating.app.repository.RatingRepository;
import com.rating.app.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating updateRating(Long id, Rating updatedRating) {
        return ratingRepository.findById(id)
                .map(existingRating -> {
                    existingRating.setValue(updatedRating.getValue());
                    return ratingRepository.save(existingRating);
                })
                .orElseThrow(() -> new RuntimeException("Rating with ID " + id + " not found"));
    }

    @Override
    public void deleteRatingById(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating getRatingByUserIdAndProductId(Long userId, Long productId) {
        return ratingRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Rating not found for userId: " + userId + " and productId: " + productId));
    }

	@Override
	public List<Rating> getRatingByUserId(Long userId) {
		return ratingRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException(" findByUserId not found"));
	}

	@Override
	public List<Rating> getRatingByProductId(Long productId) {
		System.out.println("*********************** rating service 1");

		return ratingRepository.findByProductId(productId).orElseThrow(()-> new RuntimeException(" findByProductId not found"));

	}
}

