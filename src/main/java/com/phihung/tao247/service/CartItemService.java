package com.phihung.tao247.service;

import com.phihung.tao247.exception.CartItemException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.CartItem;
import com.phihung.tao247.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String color, Long userId);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
