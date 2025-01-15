package com.crio.order_management_system.service;

import com.crio.order_management_system.entities.GroceryItem;
import com.crio.order_management_system.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    @Autowired
    public GroceryItemService(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }


    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public Optional<GroceryItem> getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id);
    }

    public void deleteGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem createGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }
}