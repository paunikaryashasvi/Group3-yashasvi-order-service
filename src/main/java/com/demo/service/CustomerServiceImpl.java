package com.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dto.CustomerDTO;
import com.demo.entity.Customer;
import com.demo.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer, CustomerDTO.class);
        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));

        // Update existingCustomer fields with values from customerDTO
        modelMapper.map(customerDTO, existingCustomer);

        customerRepository.save(existingCustomer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

