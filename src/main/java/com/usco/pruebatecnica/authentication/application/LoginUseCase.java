package com.usco.pruebatecnica.authentication.application;

import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(UserRepository _userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = _userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Invalid credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalStateException("Invalid credentials");
        }

        return user;
    }
}
