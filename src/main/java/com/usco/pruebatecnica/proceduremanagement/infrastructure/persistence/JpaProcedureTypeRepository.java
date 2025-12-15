package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProcedureTypeRepository implements ProcedureTypeRepository {
    private final SpringDataProcedureTypeRepository repository;

    public JpaProcedureTypeRepository(SpringDataProcedureTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProcedureType> findAll() {
        return repository.findAll().stream()
                .map(ProcedureTypeMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProcedureType> findById(Long id) {
        return repository.findById(id)
                .map(ProcedureTypeMapper::toDomain);
    }
}
