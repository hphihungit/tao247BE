package com.phihung.tao247.repository;

import com.phihung.tao247.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("SELECT p FROM Product p" +
//            "WHERE (p.category.name=:category OR :category = '')" +
//            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.beforeDiscount BETWEEN :minPrice AND :maxPrice)) " +
//            "AND (:minDiscount IS NULL OR p.percentsDiscount >= :minDiscount) " +
//            "ORDER BY" +
//            "CASE WHEN :sort = 'price_low' THEN p.beforeDisoucnt END ASC, " +
//            "CASE WHEN :sort = 'price_high' THEN p.beforeDisoucnt END DESC, "
//    )
    @Query("SELECT p FROM Product p " +
            "WHERE (COALESCE(p.category.name, '') = :category OR :category = '') " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.price BETWEEN :minPrice AND :maxPrice)) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.beforeDiscount END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.beforeDiscount END DESC")
    public List<Product> filterProduct(@Param("category") String category,
                                       @Param("minPrice") Integer minPrice,
                                       @Param("maxPrice") Integer maxPrice,
                                       @Param("sort") String sort);

}
