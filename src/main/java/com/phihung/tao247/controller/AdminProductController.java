package com.phihung.tao247.controller;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.request.CreateProductRequest;
import com.phihung.tao247.response.ApiResponse;
import com.phihung.tao247.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Xóa thành công sản phẩm");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.findAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product request,
                                                 @PathVariable Long productId) throws ProductException {
        Product product = productService.updateProduct(productId, request);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] requests) {
        for (CreateProductRequest product: requests) {
            productService.createProduct(product);
        }

        ApiResponse res = new ApiResponse();
        res.setMessage("Tạo sản phẩm thành công");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
