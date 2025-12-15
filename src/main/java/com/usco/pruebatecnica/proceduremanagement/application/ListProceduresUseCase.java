package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProceduresUseCase {
    private final ProcedureRepository procedureRepository;

    public ListProceduresUseCase(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> execute() {
        return procedureRepository.findAll();
    }
}
