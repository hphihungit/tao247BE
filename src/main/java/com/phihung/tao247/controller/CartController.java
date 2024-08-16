package com.phihung.tao247.controller;

import com.phihung.tao247.exception.ProductException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.Cart;
import com.phihung.tao247.model.User;
import com.phihung.tao247.request.AddItemRequest;
import com.phihung.tao247.response.ApiResponse;
import com.phihung.tao247.service.CartService;
import com.phihung.tao247.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "Thêm sản phẩm vào giỏ hàng")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
//    @Operation(description = "Tìm giỏ hàng bằng id người dùng")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(description = "Thêm sản phẩm vào giỏ hàng")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest request,
                                                     @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), request);

        ApiResponse res = new ApiResponse();
        res.setMessage("Đã thêm sản phẩm vào giỏ hàng");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
