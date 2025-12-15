package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureRepository;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;
import org.springframework.stereotype.Service;

@Service
public class UpdateProcedureStateUseCase {
    private final ProcedureRepository procedureRepository;

    public UpdateProcedureStateUseCase(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public void execute(Long procedureId, ProcedureState newState, Long servantId) {

        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Trámite no encontrado")
                );

        procedure.changeState(newState, servantId);

        procedureRepository.save(procedure);
    }
}
