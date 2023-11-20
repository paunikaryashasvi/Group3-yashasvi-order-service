package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customer;
import com.demo.entity.Order;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Add custom queries if needed

	

}
