package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.CartDTO;
import com.demo.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long customerId, @RequestBody CartDTO cartDTO) {
        return cartService.createCart(customerId, cartDTO);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartDTO>> getCartsByCustomerId(@PathVariable Long customerId) {
        return cartService.getCartsByCustomerId(customerId);
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Void> updateCart(@PathVariable Long cartId, @RequestBody CartDTO cartDTO) {
        return cartService.updateCart(cartId, cartDTO);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId);
    }
}

