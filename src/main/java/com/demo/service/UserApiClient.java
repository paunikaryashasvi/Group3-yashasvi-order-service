package com.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.dto.UserDTO;

@FeignClient(name = "user-service", url = "http://localhost:9093/api/user") // Replace with the actual host and port
public interface UserApiClient {

    @GetMapping("/id/{userId}")
    ResponseEntity<UserDTO> getUserById(@PathVariable("userId") long id);

    // Add other user-related methods as needed
}
