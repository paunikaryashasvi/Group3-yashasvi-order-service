package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    // Add custom queries if needed
}
