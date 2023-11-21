package com.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.CartDTO;
import com.demo.entity.Cart;
import com.demo.entity.Customer;
import com.demo.repository.CartRepository;
import com.demo.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<CartDTO> createCart(Long customerId, CartDTO cartDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

        Cart cart = modelMapper.map(cartDTO, Cart.class);
        cart.setCustomer(customer);

        Cart savedCart = cartRepository.save(cart);
        CartDTO savedCartDTO = modelMapper.map(savedCart, CartDTO.class);

        return new ResponseEntity<>(savedCartDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CartDTO> getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartDTO>> getCartsByCustomerId(Long customerId) {
        Optional<Cart> carts = cartRepository.findById(customerId);
        List<CartDTO> cartDTOs = carts.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(cartDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOs = carts.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(cartDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateCart(Long cartId, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));

        // Update existingCart fields with values from cartDTO
        modelMapper.map(cartDTO, existingCart);

        cartRepository.save(existingCart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
