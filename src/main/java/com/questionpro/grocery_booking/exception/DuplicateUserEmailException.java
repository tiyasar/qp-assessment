package com.questionpro.grocery_booking.exception;

public class DuplicateUserEmailException extends RuntimeException {
    public DuplicateUserEmailException() {
        super("Email already exists!");
    }

    public DuplicateUserEmailException(String message) {
        super(message);
    }
}
