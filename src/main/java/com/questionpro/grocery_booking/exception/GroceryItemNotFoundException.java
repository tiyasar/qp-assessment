package com.questionpro.grocery_booking.exception;

public class GroceryItemNotFoundException extends RuntimeException {
    public GroceryItemNotFoundException() {
        super("Invalid grocery item ID!");
    }

    public GroceryItemNotFoundException(String message) {
        super(message);
    }
}
