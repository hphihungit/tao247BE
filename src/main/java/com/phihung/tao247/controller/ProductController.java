package com.phihung.tao247.controller;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
                                                                      @RequestParam List<Integer> capacity,
                                                                      @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber,
                                                                      @RequestParam Integer pageSize) {

        Page<Product> res = productService.getAllProduct(category, capacity, minPrice, maxPrice, sort, stock, pageNumber, pageSize);
        System.out.println("Hoàn thành sản phẩm");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }



}
