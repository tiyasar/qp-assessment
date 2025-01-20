package com.questionpro.grocery_booking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotEmpty(message = "Order items should not be empty")
    @Valid
    private List<OrderItemDto> orderItems;
}
