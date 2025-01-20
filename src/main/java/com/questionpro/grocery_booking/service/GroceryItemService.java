package com.questionpro.grocery_booking.service;

import com.questionpro.grocery_booking.dto.GroceryItemDto;
import com.questionpro.grocery_booking.entity.GroceryItem;
import com.questionpro.grocery_booking.exception.DuplicateGroceryItemException;
import com.questionpro.grocery_booking.exception.GroceryItemNotFoundException;
import com.questionpro.grocery_booking.repository.GroceryItemRepository;
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

    public GroceryItem createGroceryItem(GroceryItemDto groceryItemDto) {
        Optional<GroceryItem> groceryItem = groceryItemRepository.findByName(groceryItemDto.getName());
        if (groceryItem.isPresent()) {
            throw new DuplicateGroceryItemException();
        }
        GroceryItem newGroceryItem = new GroceryItem();
        newGroceryItem.setName(groceryItemDto.getName());
        newGroceryItem.setPrice(groceryItemDto.getPrice());
        newGroceryItem.setInventory(groceryItemDto.getInventory());
        groceryItemRepository.save(newGroceryItem);
        return newGroceryItem;
    }

    public List<GroceryItem> getGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem getGroceryItem(Long groceryItemId) {
        return groceryItemRepository.findById(groceryItemId).orElseThrow(GroceryItemNotFoundException::new);
    }

    public GroceryItem updateGroceryItem(Long groceryItemId, GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = groceryItemRepository.findById(groceryItemId).orElseThrow(GroceryItemNotFoundException::new);
        if (groceryItemDto.getName()!=null && !groceryItemDto.getName().isBlank()) {
            groceryItem.setName(groceryItemDto.getName());
        }
        if (groceryItemDto.getPrice()!=null) {
            groceryItem.setPrice(groceryItemDto.getPrice());
        }
        if (groceryItemDto.getInventory()!=null) {
            groceryItem.setInventory(groceryItemDto.getInventory());
        }
        groceryItemRepository.save(groceryItem);
        return groceryItem;
    }

    public void deleteGroceryItem(Long groceryItemId) {
        groceryItemRepository.findById(groceryItemId).orElseThrow(GroceryItemNotFoundException::new);
        groceryItemRepository.deleteById(groceryItemId);
    }

    public void updateGroceryItemData(Long groceryItemId, GroceryItem groceryItem) {
        groceryItemRepository.findById(groceryItemId).orElseThrow(GroceryItemNotFoundException::new);
        groceryItemRepository.save(groceryItem);
    }
}
