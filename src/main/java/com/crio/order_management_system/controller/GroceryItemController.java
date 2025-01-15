package com.crio.order_management_system.controller;

import com.crio.order_management_system.entities.GroceryItem;
import com.crio.order_management_system.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class GroceryItemController {
    private final GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemService.getAllGroceryItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
        Optional<GroceryItem> groceryItem = groceryItemService.getGroceryItemById(id);
        if(groceryItem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(groceryItem.get(), HttpStatus.OK);
    }

    @PostMapping("/items")
    public GroceryItem createGroceryItem(@RequestBody GroceryItem item) {
        return groceryItemService.createGroceryItem(item);
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }
}
