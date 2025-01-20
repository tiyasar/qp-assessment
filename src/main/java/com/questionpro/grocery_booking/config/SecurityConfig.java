package com.questionpro.grocery_booking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionpro.grocery_booking.dto.ApiResponse;
import com.questionpro.grocery_booking.filter.JwtFilter;
import com.questionpro.grocery_booking.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtFilter;
    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtFilter jwtFilter, ObjectMapper objectMapper) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .authenticationEntryPoint(customAuthenticationEntryPoint())
                                .accessDeniedHandler(customAccessDeniedHandler()))
                .build();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            Throwable cause = authException.getCause();
            String unauthorizedMessage = "Unauthorized!";
            if (cause instanceof UsernameNotFoundException) {
                unauthorizedMessage = cause.getMessage();
            }
            ApiResponse<?> unauthorizedResponse = ApiResponse.builder()
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .message(unauthorizedMessage)
                    .data(null)
                    .error(null)
                    .build();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(unauthorizedResponse));
        };
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            ApiResponse<?> forbiddenResponse = ApiResponse.builder()
                    .statusCode(HttpStatus.FORBIDDEN.value())
                    .message("Access Denied!")
                    .data(null)
                    .error(null)
                    .build();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(forbiddenResponse));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
