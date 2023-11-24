package com.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dto.PaymentDTO;

@FeignClient(name = "payment-service", url = "http://localhost:9094/api/payments") // Replace with the actual host and port

public interface PaymentApiClient {

    @PostMapping("/process/{orderId}")
    ResponseEntity<Void> processPayment(@PathVariable Long orderId, @RequestBody PaymentDTO paymentDTO);

//	ResponseEntity<Void> processPayment(Long payment);

    // Add other payment-related methods as needed
}
