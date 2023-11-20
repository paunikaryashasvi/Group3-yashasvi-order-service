package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.OrderDTO;
import com.demo.entity.OrderStatus;
import com.demo.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
//        return orderService.getOrdersByCustomerId(customerId);
//    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/status/{newStatus}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus newStatus) {
        return orderService.updateOrderStatus(orderId, newStatus);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}
