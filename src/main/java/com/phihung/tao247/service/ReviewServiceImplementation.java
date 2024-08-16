package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.model.Review;
import com.phihung.tao247.model.User;
import com.phihung.tao247.repository.ProductRepository;
import com.phihung.tao247.repository.ReviewRepository;
import com.phihung.tao247.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.productRepository =  productRepository;
    }

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
