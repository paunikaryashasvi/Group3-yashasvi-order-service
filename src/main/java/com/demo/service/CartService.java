package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.dto.CartDTO;

import java.util.List;

public interface CartService {

    ResponseEntity<CartDTO> createCart(Long customerId, CartDTO cartDTO);

    ResponseEntity<CartDTO> getCartById(Long cartId);

    ResponseEntity<List<CartDTO>> getCartsByCustomerId(Long customerId);

    ResponseEntity<List<CartDTO>> getAllCarts();

    ResponseEntity<Void> updateCart(Long cartId, CartDTO cartDTO);

    ResponseEntity<Void> deleteCart(Long cartId);
}
