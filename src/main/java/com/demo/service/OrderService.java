package com.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.demo.dto.OrderDTO;
import com.demo.entity.OrderStatus;

public interface OrderService {

    ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO);

    ResponseEntity<OrderDTO> getOrderById(Long orderId);

//    ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(Long customerId);

    ResponseEntity<List<OrderDTO>> getAllOrders();

    ResponseEntity<Void> updateOrderStatus(Long orderId, OrderStatus newStatus);

    ResponseEntity<Void> deleteOrder(Long orderId);
}
