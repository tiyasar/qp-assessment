package com.questionpro.grocery_booking.controller;

import com.questionpro.grocery_booking.dto.ApiResponse;
import com.questionpro.grocery_booking.dto.AuthResponseDto;
import com.questionpro.grocery_booking.dto.LoginDto;
import com.questionpro.grocery_booking.dto.SignUpDto;
import com.questionpro.grocery_booking.entity.User;
import com.questionpro.grocery_booking.service.UserDetailsServiceImpl;
import com.questionpro.grocery_booking.service.UserService;
import com.questionpro.grocery_booking.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, UserDetailsServiceImpl userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ApiResponse<User> createUser(@RequestBody @Valid SignUpDto signUpDto) {
        User user = userService.createUser(signUpDto);
        return ApiResponse.<User>builder()
                .statusCode(HttpStatus.OK.value())
                .message("User created successfully")
                .data(user)
                .error(null)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDto> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            AuthResponseDto authResponseDto = AuthResponseDto.builder()
                    .accessToken(jwt)
                    .build();
            return ApiResponse.<AuthResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Logged in successfully")
                    .data(authResponseDto)
                    .error(null)
                    .build();
        } catch (Exception e) {
            throw new BadCredentialsException("Username or password is incorrect");
        }
    }
}
