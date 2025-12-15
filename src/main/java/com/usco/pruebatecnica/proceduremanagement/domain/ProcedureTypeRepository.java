package com.usco.pruebatecnica.proceduremanagement.domain;

import java.util.List;
import java.util.Optional;

public interface ProcedureTypeRepository {
    List<ProcedureType> findAll();
    Optional<ProcedureType> findById(Long id);
}
