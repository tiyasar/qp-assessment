package com.questionpro.grocery_booking.controller;

import com.questionpro.grocery_booking.dto.ApiResponse;
import com.questionpro.grocery_booking.dto.OrderDto;
import com.questionpro.grocery_booking.entity.Order;
import com.questionpro.grocery_booking.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/order")
@Slf4j
public class UserController {
    private final OrderService orderService;

    @Autowired
    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Order> createOrder(@RequestBody @Valid OrderDto orderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = orderService.createOrder(orderDto, authentication.getName());
        return ApiResponse.<Order>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Order placed successfully")
                .data(order)
                .error(null)
                .build();
    }
}
