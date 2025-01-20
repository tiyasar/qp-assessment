package com.questionpro.grocery_booking.service;

import com.questionpro.grocery_booking.dto.SignUpDto;
import com.questionpro.grocery_booking.entity.User;
import com.questionpro.grocery_booking.exception.DuplicateUserEmailException;
import com.questionpro.grocery_booking.exception.UserEmailNotFoundException;
import com.questionpro.grocery_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SignUpDto signUpDto) {
        Optional<User> existsUser = userRepository.findByEmail(signUpDto.getEmail());
        if (existsUser.isPresent()) {
            throw new DuplicateUserEmailException();
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(signUpDto.getRole());
        userRepository.save(user);
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserEmailNotFoundException::new);
    }
}
