package com.usco.pruebatecnica.commentsandfollowupontheprocess.application;

import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.Tracing;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.TracingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddTracingUseCase {
    private final TracingRepository repository;

    public AddTracingUseCase(TracingRepository repository) {
        this.repository = repository;
    }

    public void execute(Long procedureId, Long userId, String comment) {

        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío");
        }

        repository.save(new Tracing(procedureId, userId, comment));
    }
}
