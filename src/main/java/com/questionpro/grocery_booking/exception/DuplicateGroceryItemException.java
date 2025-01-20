package com.questionpro.grocery_booking.exception;

public class DuplicateGroceryItemException extends RuntimeException {
    public DuplicateGroceryItemException() {
        super("Grocery item already exists!");
    }

    public DuplicateGroceryItemException(String message) {
        super(message);
    }
}
