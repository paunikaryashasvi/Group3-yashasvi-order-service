package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.dto.LineItemDTO;

import java.util.List;

public interface LineItemService {

    ResponseEntity<LineItemDTO> addLineItem(Long cartId, LineItemDTO lineItemDTO);

    ResponseEntity<LineItemDTO> getLineItemById(Long lineItemId);

    ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(Long cartId);

    ResponseEntity<List<LineItemDTO>> getAllLineItems();

    ResponseEntity<Void> updateLineItem(Long lineItemId, LineItemDTO lineItemDTO);

    ResponseEntity<Void> deleteLineItem(Long lineItemId);
}
