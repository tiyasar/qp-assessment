package com.questionpro.grocery_booking.dto;

import com.questionpro.grocery_booking.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
}
