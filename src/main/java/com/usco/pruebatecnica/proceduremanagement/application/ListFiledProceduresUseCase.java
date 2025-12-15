package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureRepository;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListFiledProceduresUseCase {
    private final ProcedureRepository procedureRepository;

    public ListFiledProceduresUseCase(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> execute(Long servantId) {
        if (servantId == null) {
            throw new IllegalArgumentException("servantId es requerido");
        }
        return procedureRepository.findByStateAndUserServantId(ProcedureState.FILED, servantId);
    }
}
