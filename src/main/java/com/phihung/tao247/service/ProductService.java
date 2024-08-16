package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product request) throws ProductException;
    public Product findProductById(Long id) throws ProductException;
    public List<Product> findProductByCategory(String category);
    public Page<Product> getAllProduct(String category, List<Integer> capacity, Integer minPrice, Integer maxPrice
            , String sort, String stock, Integer pageNumber, Integer pageSize);

    public List<Product> findAllProduct();
}
