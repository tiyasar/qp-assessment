package com.questionpro.grocery_booking.dto;

import com.questionpro.grocery_booking.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Email is invalid")
    private String email;
    @NotBlank(message = "Password should not be blank")
    private String password;
    @NotNull(message = "Role should not be null")
    private Role role;
}
