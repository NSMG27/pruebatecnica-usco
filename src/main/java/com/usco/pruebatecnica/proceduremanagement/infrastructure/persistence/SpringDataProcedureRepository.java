package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProcedureRepository extends JpaRepository<ProcedureEntity, Long> {
    List<ProcedureEntity> findByState(ProcedureState state);
    List<ProcedureEntity> findByStateAndUserServantId(ProcedureState state, Long userServantId);
}
