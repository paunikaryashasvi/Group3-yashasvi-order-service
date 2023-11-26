package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.LineItemDTO;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.service.LineItemService;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class LineItemController {

    private final LineItemService lineItemService;

    @Autowired
    public LineItemController(LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    @PostMapping("/{cartId}/line-items")
    public ResponseEntity<LineItemDTO> addLineItem(@PathVariable Long cartId, @RequestBody LineItemDTO lineItemDTO) {
        return lineItemService.addLineItem(cartId, lineItemDTO);
    }

    @DeleteMapping("/{cartId}/line-items/{lineItemId}")
    public ResponseEntity<Void> removeLineItem(@PathVariable Long cartId, @PathVariable Long lineItemId)throws ResourceNotFoundException {
        return lineItemService.removeLineItem(cartId, lineItemId);
    }

    @GetMapping("/{cartId}/line-items")
    public ResponseEntity<List<LineItemDTO>> getLineItemsByCartId(@PathVariable Long cartId) throws ResourceNotFoundException {
        return lineItemService.getLineItemsByCartId(cartId);
    }

    @PutMapping("/{cartId}/line-items/{lineItemId}")
    public ResponseEntity<LineItemDTO> updateLineItem(@PathVariable Long cartId, @PathVariable Long lineItemId, @RequestBody LineItemDTO lineItemDTO) {
        return lineItemService.updateLineItem(cartId, lineItemId, lineItemDTO);
    }

    @GetMapping("/{cartId}/line-items/{lineItemId}")
    public ResponseEntity<LineItemDTO> getLineItemById(@PathVariable Long cartId, @PathVariable Long lineItemId) {
        return lineItemService.getLineItemById(cartId, lineItemId);
    }
}
