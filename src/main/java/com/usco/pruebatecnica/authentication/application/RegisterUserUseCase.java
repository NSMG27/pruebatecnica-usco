package com.usco.pruebatecnica.authentication.application;

import com.usco.pruebatecnica.authentication.domain.Role;
import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(String name, String email, String password, Role rol) {
        String hashed = passwordEncoder.encode(password);
        User user = new User(name, email, hashed, rol);
        userRepository.save(user);
    }
}
