package com.usco.pruebatecnica.commentsandfollowupontheprocess.infrastructure.controller;

import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.domain.UserRepository;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.application.AddTracingUseCase;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.application.ListTracingByProcedureUseCase;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.Tracing;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramites")
public class TracingController {

    private final AddTracingUseCase addTracingUseCase;
    private final ListTracingByProcedureUseCase listTracingUseCase;
    private final UserRepository userRepository;

    public TracingController(AddTracingUseCase addTracingUseCase,
                             ListTracingByProcedureUseCase listTracingUseCase,
                             UserRepository userRepository) {
        this.addTracingUseCase = addTracingUseCase;
        this.listTracingUseCase = listTracingUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}/seguimiento")
    public ResponseEntity<?> addTracing(
            @PathVariable Long id,
            @RequestBody AddTracingRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName();

        Long userId = userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

        addTracingUseCase.execute(id, userId, request.comment());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/seguimiento")
    public ResponseEntity<List<Tracing>> listTracing(@PathVariable Long id) {
        return ResponseEntity.ok(
                listTracingUseCase.execute(id)
        );
    }
}

record AddTracingRequest(String comment) {}
