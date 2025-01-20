package com.questionpro.grocery_booking.repository;

import com.questionpro.grocery_booking.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    Optional<GroceryItem> findByName(String name);
}
