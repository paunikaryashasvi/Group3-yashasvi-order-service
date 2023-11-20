package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Add custom queries if needed
}
