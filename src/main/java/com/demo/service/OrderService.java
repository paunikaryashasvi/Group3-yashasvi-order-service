package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    ResponseEntity<OrderDTO> createOrder(Long userId, OrderDTO orderDTO);

    ResponseEntity<OrderDTO> getOrderById(Long orderId);

    ResponseEntity<List<OrderDTO>> getOrdersByUserId(Long userId);

    ResponseEntity<List<OrderDTO>> getAllOrders();

    ResponseEntity<Void> updateOrder(Long orderId, OrderDTO orderDTO);

    ResponseEntity<Void> deleteOrder(Long orderId);
}

