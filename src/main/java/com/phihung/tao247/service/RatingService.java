package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Rating;
import com.phihung.tao247.model.User;
import com.phihung.tao247.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    public Rating createRating(RatingRequest request, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);


}
