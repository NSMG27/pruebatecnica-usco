package com.usco.pruebatecnica.commentsandfollowupontheprocess.application;

import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.Tracing;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.TracingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTracingByProcedureUseCase {
    private final TracingRepository repository;

    public ListTracingByProcedureUseCase(TracingRepository repository) {
        this.repository = repository;
    }

    public List<Tracing> execute(Long procedureId) {
        return repository.findByProcedureId(procedureId);
    }
}
