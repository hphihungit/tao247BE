package com.phihung.tao247.service;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.CartItem;
import com.phihung.tao247.model.Product;
import com.phihung.tao247.model.User;
import com.phihung.tao247.repository.CartRepository;
import com.phihung.tao247.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    private UserService userService;

    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService, ProductService productService, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.userService = userService;
    }
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

//    @Override
//    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUserId(userId);
//            cart = cartRepository.save(cart); // Lưu giỏ hàng mới vào database
//        }
//        Product product = productService.findProductById(request.getProductId());
//        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getColor(), userId);
//
//        if (isPresent == null) {
//            CartItem cartItem = new CartItem();
//            cartItem.setProduct(product);
//            cartItem.setCart(cart);
//            cartItem.setQuantity(request.getQuantity());
//            cartItem.setUserId(userId);
//
//            int price = request.getQuantity() * product.getPrice();
//            cartItem.setPrice(price);
//            cartItem.setColor(request.getColor());
//
//            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
//            cart.getCartItems().add(createdCartItem);
//        }
//
//        return "Đã thêm vào giỏ hàng";
//    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            User user = null; // Tìm kiếm User dựa vào userId
            try {
                user = userService.findUserById(userId);
            } catch (UserException e) {
                throw new RuntimeException(e);
            }
            cart.setUser(user); // Thiết lập User cho Cart
            cart = cartRepository.save(cart); // Lưu giỏ hàng mới vào database
        }

        Product product = productService.findProductById(request.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getColor(), userId);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());

            int price = request.getQuantity() * product.getPrice();
            cartItem.setPrice(price);
            cartItem.setColor(request.getColor());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            if (createdCartItem != null) {
                cart.getCartItems().add(createdCartItem);
            } else {
                return "Lỗi: Không thể tạo sản phẩm trong giỏ hàng";
            }
        }

        return "Đã thêm vào giỏ hàng";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        int totalBeforeDiscount = 0;
        int totalItem = 0;

        for (CartItem cartItem: cart.getCartItems()) {
            totalBeforeDiscount += cartItem.getBeforeDiscount();
            totalPrice += cartItem.getPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalBeforeDiscount(totalBeforeDiscount);
        cart.setDiscount(totalBeforeDiscount - totalPrice);

        return cartRepository.save(cart);
    }
}
