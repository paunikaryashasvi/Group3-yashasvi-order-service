package com.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.LineItemDTO;
import com.demo.entity.Cart;
import com.demo.entity.LineItem;
import com.demo.repository.CartRepository;
import com.demo.repository.LineItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<LineItemDTO> addLineItem(Long cartId, LineItemDTO lineItemDTO) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));

        LineItem lineItem = modelMapper.map(lineItemDTO, LineItem.class);
        lineItem.setCart(cart);

        LineItem savedLineItem = lineItemRepository.save(lineItem);
        LineItemDTO savedLineItemDTO = modelMapper.map(savedLineItem, LineItemDTO.class);

        return new ResponseEntity<>(savedLineItemDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LineItemDTO> getLineItemById(Long lineItemId) {
        LineItem lineItem = lineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new LineItemNotFoundException("LineItem not found with ID: " + lineItemId));

        LineItemDTO lineItemDTO = modelMapper.map(lineItem, LineItemDTO.class);
        return new ResponseEntity<>(lineItemDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(Long cartId) {
        Optional<LineItem> lineItems = lineItemRepository.findById(cartId);
        List<LineItemDTO> lineItemDTOs = lineItems.stream()
                .map(lineItem -> modelMapper.map(lineItem, LineItemDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(lineItemDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LineItemDTO>> getAllLineItems() {
        List<LineItem> lineItems = lineItemRepository.findAll();
        List<LineItemDTO> lineItemDTOs = lineItems.stream()
                .map(lineItem -> modelMapper.map(lineItem, LineItemDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(lineItemDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateLineItem(Long lineItemId, LineItemDTO lineItemDTO) {
        LineItem existingLineItem = lineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new LineItemNotFoundException("LineItem not found with ID: " + lineItemId));

        // Update existingLineItem fields with values from lineItemDTO
        modelMapper.map(lineItemDTO, existingLineItem);

        lineItemRepository.save(existingLineItem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteLineItem(Long lineItemId) {
        lineItemRepository.deleteById(lineItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
