package com.crio.order_management_system.repository;

import com.crio.order_management_system.entities.Customer;
import com.crio.order_management_system.entities.GroceryItem;
import com.crio.order_management_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
