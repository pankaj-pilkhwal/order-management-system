package com.crio.order_management_system.repository;

import com.crio.order_management_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}