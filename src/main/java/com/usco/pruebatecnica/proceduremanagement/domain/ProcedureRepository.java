package com.usco.pruebatecnica.proceduremanagement.domain;

import java.util.List;
import java.util.Optional;

public interface ProcedureRepository {
    void save(Procedure procedure);
    List<Procedure> findAll();
    Optional<Procedure> findById(Long id);
    List<Procedure> findByState(ProcedureState state);

    List<Procedure> findByStateAndUserServantId(ProcedureState state, Long userServantId);
}
