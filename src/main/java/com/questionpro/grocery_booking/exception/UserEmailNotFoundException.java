package com.questionpro.grocery_booking.exception;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException() {
        super("User email does not exists!");
    }

    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
