package com.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.OrderDTO;
import com.demo.entity.Order;
import com.demo.entity.OrderStatus;
import com.demo.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PaymentApiClient paymentApiClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, PaymentApiClient paymentApiClient) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.paymentApiClient = paymentApiClient;
    }

    @Override
    public ResponseEntity<OrderDTO> createOrder(Long userId, OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderRepository.save(order);

//        ResponseEntity<Void> paymentResponse = paymentApiClient.processPayment(orderDTO.getId(), null);
//
//        if (paymentResponse.getStatusCode().is2xxSuccessful()) {
//            // Payment successful, update order status or perform other actions
//            order.setOrderStatus(OrderStatus.CONFIRMED);
//            orderRepository.save(order);
        
        OrderDTO createdOrderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDTO);
//        else {
//            // Handle payment failure
//            return ResponseEntity.status(paymentResponse.getStatusCode()).build();
//        }
    }

    @Override
    public ResponseEntity<OrderDTO> getOrderById(Long orderId) {
        Order order = getOrderEntityById(orderId);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(Long userId) {
        Optional<Order> orders = orderRepository.findById(userId);
        List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOs);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOs);
    }

    @Override
    public ResponseEntity<Void> updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = getOrderEntityById(orderId);

        // Update fields based on your requirements
        modelMapper.map(orderDTO, existingOrder);

        orderRepository.save(existingOrder);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        Order order = getOrderEntityById(orderId);
        orderRepository.delete(order);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Order getOrderEntityById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}

