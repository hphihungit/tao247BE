package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.model.Rating;
import com.phihung.tao247.model.User;
import com.phihung.tao247.repository.RatingRepository;
import com.phihung.tao247.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{
    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductRating(productId);
    }
}
