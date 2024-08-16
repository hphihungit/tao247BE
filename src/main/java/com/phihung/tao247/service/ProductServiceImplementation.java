package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Category;
import com.phihung.tao247.model.Color;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.repository.CategoryRepository;
import com.phihung.tao247.repository.ProductRepository;
import com.phihung.tao247.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService{
    private ProductRepository productRepository;
    private final UserService userService;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());

        if (topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(), topLevel.getName());

        if (secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(), secondLevel.getName());

        if (thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product product = new Product();
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setBeforeDiscount(request.getBeforeDiscount());
        product.setPercentsDiscount(request.getPercentsDiscount());
        product.setQuantity(request.getQuantity());
        product.setCapacity(request.getCapacity());
//        product.setColor(request.getColors());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Set<Color> colors = request.getColors();
        product.setColor(colors);

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getColor().clear();
        productRepository.delete(product);
        return "Xóa thành công sản phẩm";
    }

    @Override
    public Product updateProduct(Long productId, Product request) throws ProductException {
        Product product = findProductById(productId);

        if (request.getQuantity() != 0) {
            product.setQuantity(request.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new ProductException("Không tìm thấy sản phẩm ứng với id - " + id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<Integer> capacity, Integer minPrice, Integer maxPrice, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, sort);

//        if (!capacity.isEmpty()) {
//            // Lọc sản phẩm theo capacity
//            products = products.stream()
//                    .filter(p -> capacity.stream().anyMatch(c -> c.equals(p.getCapacity())))
//                    .collect(Collectors.toList());
//        }
        if (!capacity.isEmpty()) {
            products = products.stream()
                    .filter(p -> capacity.contains(p.getCapacity()))
                    .collect(Collectors.toList());
        }

        // Lọc theo stock
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        if (minPrice != null && maxPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        int startIndex = (int) pageable.getOffset();
//        int startIndex = Math.min((int) pageable.getOffset(), products.size());
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

        return filteredProducts;
    }

    @Override
    public List<Product> findAllProduct() {
        return null;
    }
}
