package com.usco.pruebatecnica.authentication.infrastructure.controller;

import com.usco.pruebatecnica.authentication.application.LoginUseCase;
import com.usco.pruebatecnica.authentication.application.RegisterUserUseCase;
import com.usco.pruebatecnica.authentication.domain.Role;
import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.infrastructure.security.JwtService;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUser;
    private final LoginUseCase loginUseCase;
    private final JwtService jwtService;

    public AuthController(RegisterUserUseCase registerUser,
                          LoginUseCase loginUseCase,
                          JwtService jwtService) {
        this.registerUser = registerUser;
        this.loginUseCase = loginUseCase;
        this.jwtService = jwtService;
    }

    // ---------------- REGISTER ----------------

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            registerUser.execute(
                    req.name(),
                    req.email(),
                    req.password(),
                    req.rol()
            );
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno"));
        }
    }

    // ---------------- LOGIN ----------------

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            User user = loginUseCase.execute(req.email(), req.password());

            // ⚠️ Para la prueba: password simplificado
            String token = jwtService.generateToken(
                    user.getEmail(),
                    user.getRol().name()
            );

            return ResponseEntity.ok(Map.of("token", token));

        } catch (IllegalStateException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", ex.getMessage()));

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno"));
        }
    }
}

record RegisterRequest(@JsonAlias({"nombre", "name"}) String name, String email, String password, Role rol) {}
record LoginRequest(String email, String password) {}