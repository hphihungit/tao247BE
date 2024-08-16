package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.User;
import com.phihung.tao247.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException;
    public Cart findUserCart(Long userId);
}
