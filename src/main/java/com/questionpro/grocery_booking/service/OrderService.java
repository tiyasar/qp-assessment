package com.questionpro.grocery_booking.service;

import com.questionpro.grocery_booking.dto.OrderDto;
import com.questionpro.grocery_booking.entity.GroceryItem;
import com.questionpro.grocery_booking.entity.Order;
import com.questionpro.grocery_booking.entity.OrderItem;
import com.questionpro.grocery_booking.entity.User;
import com.questionpro.grocery_booking.exception.InsufficientGroceryItemException;
import com.questionpro.grocery_booking.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GroceryItemService groceryItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, GroceryItemService groceryItemService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.groceryItemService = groceryItemService;
    }

    public Order createOrder(OrderDto orderDto, String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();
        orderDto.getOrderItems().forEach(orderItemDto -> {
            GroceryItem groceryItem = groceryItemService.getGroceryItem(orderItemDto.getGroceryItemId());
            if (groceryItem.getInventory()<orderItemDto.getCount()) {
                throw new InsufficientGroceryItemException("Insufficient grocery item for ID: " + groceryItem.getId());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setGroceryItem(groceryItem);
            orderItem.setCount(orderItemDto.getCount());
            orderItems.add(orderItem);
            groceryItem.setInventory(groceryItem.getInventory()-orderItemDto.getCount());
            groceryItemService.updateGroceryItemData(groceryItem.getId(), groceryItem);
        });
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return order;
    }
}
