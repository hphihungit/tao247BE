package com.phihung.tao247.controller;

import com.phihung.tao247.exception.CartItemException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.CartItem;
import com.phihung.tao247.model.User;
import com.phihung.tao247.response.ApiResponse;
import com.phihung.tao247.service.CartItemService;
import com.phihung.tao247.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/cart_items")
//public class CartItemController {
//    @Autowired
//    private CartItemService cartItemService;
//
//    @Autowired
//    private UserService userService;
//
//    @DeleteMapping("/{cartItemId}")
//    @Operation(description = "Xóa sản phẩm trong giỏ hàng")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Xóa sản phẩm trong giỏ hàng")
//    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
//                                                      @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
//        User user = userService.findUserProfileByJwt(jwt);
//        cartItemService.removeCartItem(user.getId(), cartItemId);
//
//        ApiResponse res = new ApiResponse();
//        res.setMessage("Đã xóa sản phẩm khỏi giỏ hàng");
//        res.setStatus(true);
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }
//
////    @PutMapping("/{cartItemId}")
////    @Operation(description = "Cập nhật giỏ hàng")
////    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem,
////                                                   @PathVariable Long cartItemId,
////                                                   @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
////        User user = userService.findUserProfileByJwt(jwt);
////        CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
////
////        return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
////    }
//
//    @PutMapping("/{cartItemId}")
//    public ResponseEntity<?> updateCartItem(@RequestBody CartItem cartItem,
//                                            @PathVariable Long cartItemId,
//                                            @RequestHeader("Authorization") String jwt) {
//        try {
//            User user = userService.findUserProfileByJwt(jwt);
//            CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
//            return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
//        } catch (UserException | CartItemException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//        }
//    }
//}

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    @Operation(description = "Xóa sản phẩm trong giỏ hàng")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Xóa sản phẩm trong giỏ hàng")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
                                                      @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Đã xóa sản phẩm khỏi giỏ hàng");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItem cartItem,
                                            @PathVariable Long cartItemId,
                                            @RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUserProfileByJwt(jwt);
            CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
            return new ResponseEntity<>(updateCartItem, HttpStatus.OK);
        } catch (UserException | CartItemException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
