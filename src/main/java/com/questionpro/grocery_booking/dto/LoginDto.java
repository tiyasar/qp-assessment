package com.questionpro.grocery_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password should not be blank")
    private String password;
}
