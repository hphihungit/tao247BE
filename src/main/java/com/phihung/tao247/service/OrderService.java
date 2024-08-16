package com.phihung.tao247.service;

import com.phihung.tao247.exception.OrderException;
import com.phihung.tao247.model.Address;
import com.phihung.tao247.model.Order;
import com.phihung.tao247.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address address);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long userId);
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder(Long orderId) throws OrderException;
    public Order canceledOrder(Long orderId) throws OrderException;
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId) throws OrderException;
}
