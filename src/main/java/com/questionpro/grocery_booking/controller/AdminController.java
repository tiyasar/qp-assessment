package com.questionpro.grocery_booking.controller;

import com.questionpro.grocery_booking.dto.ApiResponse;
import com.questionpro.grocery_booking.dto.CreateGroceryItemDto;
import com.questionpro.grocery_booking.dto.GroceryItemDto;
import com.questionpro.grocery_booking.entity.GroceryItem;
import com.questionpro.grocery_booking.service.GroceryItemService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/grocery")
public class AdminController {
    private final GroceryItemService groceryItemService;

    @Autowired
    public AdminController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<GroceryItem> createGroceryItem(@RequestBody @Validated({CreateGroceryItemDto.class, Default.class}) GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = groceryItemService.createGroceryItem(groceryItemDto);
        return ApiResponse.<GroceryItem>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Grocery item created successfully")
                .data(groceryItem)
                .error(null)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<GroceryItem>> getGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemService.getGroceryItems();
        return ApiResponse.<List<GroceryItem>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Grocery items fetched successfully")
                .data(groceryItems)
                .error(null)
                .build();
    }

    @PutMapping("/{groceryItemId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<GroceryItem> updateGroceryItem(@PathVariable("groceryItemId") Long groceryItemId, @RequestBody @Valid GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = groceryItemService.updateGroceryItem(groceryItemId, groceryItemDto);
        return ApiResponse.<GroceryItem>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Grocery item updated successfully")
                .data(groceryItem)
                .error(null)
                .build();
    }

    @DeleteMapping("/{groceryItemId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteGroceryItem(@PathVariable("groceryItemId") Long groceryItemId) {
        groceryItemService.deleteGroceryItem(groceryItemId);
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Grocery item deleted successfully")
                .data(null)
                .error(null)
                .build();
    }
}
