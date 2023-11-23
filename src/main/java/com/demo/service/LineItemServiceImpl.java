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
import java.util.stream.Collectors;

@Service
public class LineItemServiceImpl implements LineItemService {

    private final CartRepository cartRepository;
    private final LineItemRepository lineItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LineItemServiceImpl(CartRepository cartRepository, LineItemRepository lineItemRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.lineItemRepository = lineItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<LineItemDTO> addLineItem(Long cartId, LineItemDTO lineItemDTO) {
        Cart cart = getCartEntityById(cartId);

        LineItem lineItem = modelMapper.map(lineItemDTO, LineItem.class);
        lineItem.setCart(cart);

        cart.getLineItems().add(lineItem);
        cart.setTotalPrice(cart.getTotalPrice() + lineItem.getTotalPrice());

        cartRepository.save(cart);

        LineItemDTO addedLineItemDTO = modelMapper.map(lineItem, LineItemDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedLineItemDTO);
    }

    @Override
    public ResponseEntity<Void> removeLineItem(Long cartId, Long lineItemId) {
        Cart cart = getCartEntityById(cartId);

        boolean itemRemoved = cart.getLineItems().removeIf(item -> item.getId().equals(lineItemId));
        if (itemRemoved) {
            cart.setTotalPrice(cart.getLineItems().stream().mapToDouble(LineItem::getTotalPrice).sum());
            cartRepository.save(cart);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(Long cartId) {
        List<LineItem> lineItems = getCartEntityById(cartId).getLineItems();
        List<LineItemDTO> lineItemDTOs = lineItems.stream().map(lineItem -> modelMapper.map(lineItem, LineItemDTO.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(lineItemDTOs);
    }

    @Override
    public ResponseEntity<LineItemDTO> updateLineItem(Long cartId, Long lineItemId, LineItemDTO lineItemDTO) {
        Cart cart = getCartEntityById(cartId);

        LineItem existingLineItem = getLineItemEntityById(lineItemId);

        // Update fields based on your requirements
        modelMapper.map(lineItemDTO, existingLineItem);

        cart.setTotalPrice(cart.getLineItems().stream().mapToDouble(LineItem::getTotalPrice).sum());
        cartRepository.save(cart);

        LineItemDTO updatedLineItemDTO = modelMapper.map(existingLineItem, LineItemDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(updatedLineItemDTO);
    }

    @Override
    public ResponseEntity<LineItemDTO> getLineItemById(Long cartId, Long lineItemId) {
        LineItem lineItem = getLineItemEntityById(lineItemId);
        LineItemDTO lineItemDTO = modelMapper.map(lineItem, LineItemDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(lineItemDTO);
    }

    private Cart getCartEntityById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
    }

    private LineItem getLineItemEntityById(Long lineItemId) {
        return lineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new RuntimeException("LineItem not found with id: " + lineItemId));
    }
}
