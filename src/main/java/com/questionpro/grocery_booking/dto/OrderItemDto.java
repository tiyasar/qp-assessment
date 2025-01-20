package com.questionpro.grocery_booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @NotNull(message = "Grocery Item ID should not be null")
    private Long groceryItemId;
    @NotNull(message = "Count should not be null")
    @Min(value = 0, message = "Count should not be negative")
    private Long count;
}
