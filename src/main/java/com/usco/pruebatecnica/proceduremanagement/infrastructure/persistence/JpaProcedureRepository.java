package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureRepository;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProcedureRepository implements ProcedureRepository {
    private final SpringDataProcedureRepository repository;

    public JpaProcedureRepository(SpringDataProcedureRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Procedure procedure) {
        ProcedureEntity entity = ProcedureMapper.toEntity(procedure);
        repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Procedure> findAll() {
        return repository.findAll().stream()
                .map(ProcedureMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Procedure> findById(Long id) {
        return repository.findById(id)
                .map(ProcedureMapper::toDomain);
    }

    @Override
    public List<Procedure> findByState(ProcedureState state) {
        return repository.findByState(state)
                .stream()
                .map(ProcedureMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Procedure> findByStateAndUserServantId(ProcedureState state, Long userServantId) {
        return repository.findByStateAndUserServantId(state, userServantId).stream()
                .map(ProcedureMapper::toDomain)
                .toList();
    }
}
