package com.phihung.tao247.service;

import com.phihung.tao247.exception.CartItemException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.CartItem;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.model.User;
import com.phihung.tao247.repository.CartItemRepository;
import com.phihung.tao247.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class CartItemServiceImplementation implements CartItemService{
//    private CartItemRepository cartItemRepository;
//    private UserService userService;
//    private CartRepository cartRepository;
//
//    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
//        this.cartItemRepository = cartItemRepository;
//        this.userService = userService;
//        this.cartRepository = cartRepository;
//    }
//
//    @Override
//    public CartItem createCartItem(CartItem cartItem) {
//        cartItem.setQuantity(1);
//        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
//        cartItem.setBeforeDiscount(cartItem.getProduct().getBeforeDiscount() * cartItem.getQuantity());
//
//        CartItem createdCartItem = cartItemRepository.save(cartItem);
//
//        return createdCartItem;
//    }
//
////    @Override
////    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
////        CartItem item = findCartItemById(id);
////        User user = userService.findUserById(item.getUserId());
////
////        if (user.getId().equals(userId)) {
////            item.setQuantity(cartItem.getQuantity());
////            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
////            item.setBeforeDiscount(item.getProduct().getBeforeDiscount() * item.getQuantity());
////        }
////        return cartItemRepository.save(item);
////    }
//
//    @Override
//    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
//        CartItem item = findCartItemById(id);
//        if (item == null) {
//            throw new CartItemException("Cart item not found");
//        }
//
//        Optional<User> userOptional = Optional.ofNullable(userService.findUserById(item.getUserId()));
//        if (!userOptional.isPresent()) {
//            throw new UserException("User not found for this cart item");
//        }
//
//        User user = userOptional.get();
//
//        if (user.getId().equals(userId)) {
//            item.setQuantity(cartItem.getQuantity());
//            item.setColor(cartItem.getColor());
//            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
//            item.setBeforeDiscount(item.getProduct().getBeforeDiscount() * item.getQuantity());
//            return cartItemRepository.save(item);
//        } else {
//            throw new CartItemException("User is not authorized to update this cart item");
//        }
//    }
//
//    @Override
//    public CartItem isCartItemExist(Cart cart, Product product, String color, Long userId) {
//        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, color, userId);
//        return cartItem;
//    }
//
//    @Override
//    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
//        CartItem cartItem = findCartItemById(cartItemId);
//        User user = userService.findUserById(cartItem.getUserId());
//        User requestUser = userService.findUserById(userId);
//
//        if (user.getId().equals(requestUser.getId())) {
//            cartItemRepository.deleteById(cartItemId);
//        } else {
//            throw new UserException("Bạn không thể xóa item của người dùng khác");
//        }
//    }
//
//    @Override
//    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
//        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
//
//        if (opt.isPresent()) {
//            return opt.get();
//        }
//
//        throw new CartItemException("CartItem không tương thích với id - " + cartItemId);
//    }
//}

@Service
public class CartItemServiceImplementation implements CartItemService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setBeforeDiscount(cartItem.getProduct().getBeforeDiscount() * cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        if (item == null) {
            throw new CartItemException("Cart item not found");
        }

        Optional<User> userOptional = Optional.ofNullable(userService.findUserById(item.getUserId()));
        if (!userOptional.isPresent()) {
            throw new UserException("User not found for this cart item");
        }

        User user = userOptional.get();

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setColor(cartItem.getColor());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setBeforeDiscount(item.getProduct().getBeforeDiscount() * item.getQuantity());
            return cartItemRepository.save(item);
        } else {
            throw new CartItemException("User is not authorized to update this cart item");
        }
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String color, Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, color, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());
        User requestUser = userService.findUserById(userId);

        if (user.getId().equals(requestUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new UserException("Bạn không thể xóa item của người dùng khác");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemException("CartItem không tương thích với id - " + cartItemId));
    }
}
