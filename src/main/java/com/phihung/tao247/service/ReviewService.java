package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Review;
import com.phihung.tao247.model.User;
import com.phihung.tao247.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest request, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
