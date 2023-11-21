package com.demo.service;

import org.springframework.http.ResponseEntity;

import com.demo.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO);

    ResponseEntity<CustomerDTO> getCustomerById(Long customerId);

    ResponseEntity<List<CustomerDTO>> getAllCustomers();

    ResponseEntity<Void> updateCustomer(Long customerId, CustomerDTO customerDTO);

    ResponseEntity<Void> deleteCustomer(Long customerId);
}
