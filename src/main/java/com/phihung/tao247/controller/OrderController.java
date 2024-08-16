package com.phihung.tao247.controller;

import com.phihung.tao247.exception.OrderException;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.Address;
import com.phihung.tao247.model.Order;
import com.phihung.tao247.model.User;
import com.phihung.tao247.service.OrderService;
import com.phihung.tao247.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);
        System.out.println("Order " + order);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        List<Order> orders = orderService.userOrderHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long orderId,
                                               @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
