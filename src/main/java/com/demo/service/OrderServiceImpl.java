package com.demo.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.OrderDTO;
import com.demo.entity.Order;
import com.demo.entity.OrderStatus;
import com.demo.repository.CustomerRepository;
import com.demo.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepository.save(order);
        OrderDTO savedOrderDTO = modelMapper.map(savedOrder, OrderDTO.class);
        return new ResponseEntity<>(savedOrderDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderDTO> getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(Long customerId) {
//        List<Order> orders = orderRepository.findByCustomerId(customerId);
//        List<OrderDTO> orderDTOs = orders.stream()
//                .map(order -> modelMapper.map(order, OrderDTO.class))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        order.setOrderStatus(newStatus);
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
