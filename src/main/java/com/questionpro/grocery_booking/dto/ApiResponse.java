package com.questionpro.grocery_booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private String error;
}
