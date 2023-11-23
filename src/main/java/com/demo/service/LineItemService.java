package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.dto.LineItemDTO;

import java.util.List;

public interface LineItemService {

    ResponseEntity<LineItemDTO> addLineItem(Long cartId, LineItemDTO lineItemDTO);

    ResponseEntity<Void> removeLineItem(Long cartId, Long lineItemId);

    ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(Long cartId);

    ResponseEntity<LineItemDTO> updateLineItem(Long cartId, Long lineItemId, LineItemDTO lineItemDTO);

    ResponseEntity<LineItemDTO> getLineItemById(Long cartId, Long lineItemId);
}
