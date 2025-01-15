package com.crio.order_management_system.service;

import com.crio.order_management_system.entities.GroceryItem;
import com.crio.order_management_system.entities.Order;
import com.crio.order_management_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final GroceryItemService groceryItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerService customerService, GroceryItemService groceryItemService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.groceryItemService = groceryItemService;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        long customerId = order.getCustomer().getId();
        if(customerService.getCustomerById(customerId).isEmpty()) {
            throw new RuntimeException("Customer with id: " + customerId + " not found.");
        }

        List<GroceryItem> groceryItems = order.getItems();
        for(var groceryItem : groceryItems) {
            if(groceryItemService.getGroceryItemById(groceryItem.getId()).isEmpty()) {
                throw new RuntimeException("Item with id: " + groceryItem.getId() + " is not present.");
            }

            if(groceryItem.getQuantity() > groceryItemService.getGroceryItemById(groceryItem.getId()).get().getQuantity()) {
                throw new RuntimeException("Quantity fo Item with id: " + groceryItem.getId() + " is more than the quantity present in stock.");
            }
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}