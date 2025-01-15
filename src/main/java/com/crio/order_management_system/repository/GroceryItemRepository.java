package com.crio.order_management_system.repository;

import com.crio.order_management_system.entities.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}
