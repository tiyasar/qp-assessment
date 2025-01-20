package com.questionpro.grocery_booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItemDto {
    @NotBlank(message = "Name should not be blank", groups = CreateGroceryItemDto.class)
    private String name;
    @NotNull(message = "Price should not be null", groups = CreateGroceryItemDto.class)
    @Min(value = 0, message = "Price should not be negative")
    private Double price;
    @NotNull(message = "Inventory should not be null", groups = CreateGroceryItemDto.class)
    @Min(value = 0, message = "Inventory should not be negative")
    private Long inventory;
}
