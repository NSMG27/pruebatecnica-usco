package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignProcedureUseCase {
    private final ProcedureRepository procedureRepository;

    public AssignProcedureUseCase(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public void execute(Long procedureId, Long userServantId) {

        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Trámite no encontrado")
                );

        procedure.assignTo(userServantId);

        procedureRepository.save(procedure);
    }
}
