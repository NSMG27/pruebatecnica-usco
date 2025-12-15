package com.usco.pruebatecnica.commentsandfollowupontheprocess.infrastructure.persistence;

import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.Tracing;
import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.TracingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaTracingRepository implements TracingRepository {
    private final SpringDataTracingRepository repository;

    public JpaTracingRepository(SpringDataTracingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Tracing tracing) {
        repository.save(TracingMapper.toEntity(tracing));
    }

    @Override
    public List<Tracing> findByProcedureId(Long procedureId) {
        return repository.findByProcedureId(procedureId)
                .stream()
                .map(TracingMapper::toDomain)
                .toList();
    }
}
