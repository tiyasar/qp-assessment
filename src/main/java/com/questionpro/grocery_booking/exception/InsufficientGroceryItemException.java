package com.questionpro.grocery_booking.exception;

public class InsufficientGroceryItemException extends RuntimeException {
    public InsufficientGroceryItemException() {
        super("Grocery item unavailable!");
    }

    public InsufficientGroceryItemException(String message) {
        super(message);
    }
}
