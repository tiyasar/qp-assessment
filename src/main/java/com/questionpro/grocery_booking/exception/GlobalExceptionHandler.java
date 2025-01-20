package com.questionpro.grocery_booking.exception;

import com.questionpro.grocery_booking.dto.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateGroceryItemException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> duplicateEmailExceptionHandler(DuplicateGroceryItemException e) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> validationExceptionHandler(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(String.join(",", errors))
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> argTypeMismatchValidationExceptionHandler(MethodArgumentTypeMismatchException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(GroceryItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> groceryItemNotFoundExceptionHandler(GroceryItemNotFoundException e) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<?> badCredentialsExceptionHandler(BadCredentialsException e) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<?> authorizationDeniedExceptionHandler(AuthorizationDeniedException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message("Access Denied!")
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> userEmailNotFoundExceptionHandler(UserEmailNotFoundException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(DuplicateUserEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> duplicateUserEmailExceptionHandler(DuplicateUserEmailException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(InsufficientGroceryItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> insufficientGroceryItemExceptionHandler(InsufficientGroceryItemException ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> exceptionHandler(Exception ex) {
        return ApiResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .data(null)
                .error(ex.toString())
                .build();
    }
}
