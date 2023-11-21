package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.LineItemDTO;
import com.demo.service.LineItemService;

import java.util.List;

@RestController
@RequestMapping("/api/lineitems")
public class LineItemController {

    @Autowired
    private LineItemService lineItemService;

    @PostMapping("/{cartId}")
    public ResponseEntity<LineItemDTO> addLineItem(@PathVariable Long cartId, @RequestBody LineItemDTO lineItemDTO) {
        return lineItemService.addLineItem(cartId, lineItemDTO);
    }

    @GetMapping("/{lineItemId}")
    public ResponseEntity<LineItemDTO> getLineItemById(@PathVariable Long lineItemId) {
        return lineItemService.getLineItemById(lineItemId);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(@PathVariable Long cartId) {
        return lineItemService.getLineItemsByCartId(cartId);
    }

    @GetMapping
    public ResponseEntity<List<LineItemDTO>> getAllLineItems() {
        return lineItemService.getAllLineItems();
    }

    @PutMapping("/{lineItemId}")
    public ResponseEntity<Void> updateLineItem(@PathVariable Long lineItemId, @RequestBody LineItemDTO lineItemDTO) {
        return lineItemService.updateLineItem(lineItemId, lineItemDTO);
    }

    @DeleteMapping("/{lineItemId}")
    public ResponseEntity<Void> deleteLineItem(@PathVariable Long lineItemId) {
        return lineItemService.deleteLineItem(lineItemId);
    }
}
