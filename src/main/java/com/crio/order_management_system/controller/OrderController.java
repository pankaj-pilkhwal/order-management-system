package com.crio.order_management_system.controller;

import com.crio.order_management_system.entities.Order;
import com.crio.order_management_system.service.CustomerService;
import com.crio.order_management_system.service.GroceryItemService;
import com.crio.order_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final CustomerService customerService;
    private final GroceryItemService groceryItemService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CustomerService customerService, GroceryItemService groceryItemService, OrderService orderService) {
        this.customerService = customerService;
        this.groceryItemService = groceryItemService;
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        if(orderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.accepted().build();
    }

}