package com.demo.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.CartDTO;
import com.demo.dto.LineItemDTO;
import com.demo.dto.UserDTO;
import com.demo.entity.Cart;
import com.demo.entity.CartStatus;
import com.demo.entity.LineItem;
import com.demo.repository.CartRepository;


import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserApiClient userApiClient;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper, UserApiClient userApiClient) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.userApiClient = userApiClient;
    }

    @Override
    public ResponseEntity<CartDTO> createCart(Long userId, CartDTO cartDTO) {
        
    	ResponseEntity<UserDTO> userResponse = userApiClient.getUserById(userId);

        if (userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null) {
            UserDTO userDTO = userResponse.getBody();
    	
    	Cart cart = modelMapper.map(cartDTO, Cart.class);
        cart.setUserId(userId);
        cartRepository.save(cart);

        CartDTO createdCartDTO = modelMapper.map(cart, CartDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartDTO);}
        else {
            // Handle user not found or other error scenarios
            return ResponseEntity.status(userResponse.getStatusCode()).build();
        }
    }

    @Override
    public ResponseEntity<CartDTO> getCartById(Long cartId) {
        Cart cart = getCartEntityById(cartId);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
    }

    @Override
    public ResponseEntity<List<CartDTO>> getCartsByUserId(Long userId) {
       
       
        ResponseEntity<UserDTO> userResponse = userApiClient.getUserById(userId);

        if (userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null) {
            UserDTO userDTO = userResponse.getBody();
            
            List<Cart> carts = cartRepository.findByUserId(userId);
            
        List<CartDTO> cartDTOs = carts.stream().map(cart -> modelMapper.map(cart, CartDTO.class)).collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(cartDTOs);}
        else {
            // Handle user not found or other error scenarios
            return ResponseEntity.status(userResponse.getStatusCode()).build();
        }
    }

    @Override
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDTO> cartDTOs = carts.stream().map(cart -> modelMapper.map(cart, CartDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(cartDTOs);
    }

    @Override
    public ResponseEntity<Void> updateCart(Long cartId, CartDTO cartDTO) {
        Cart existingCart = getCartEntityById(cartId);

        // Update fields based on your requirements
        modelMapper.map(cartDTO, existingCart);

        cartRepository.save(existingCart);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> deleteCart(Long cartId) {
        Cart cart = getCartEntityById(cartId);
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Cart getCartEntityById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    }
}
