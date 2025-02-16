package com.phihung.tao247.repository;

import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.CartItem;
import com.phihung.tao247.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci where ci.cart = :cart and ci.product = :product and ci.color = :color and ci.userId = :userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart,
                                    @Param("product")Product product,
                                    @Param("color") String color,
                                    @Param("userId") Long userId);
}
