package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListProcedureTypesUseCase {
    private final ProcedureTypeRepository procedureTypeRepository;

    public ListProcedureTypesUseCase(ProcedureTypeRepository procedureTypeRepository) {
        this.procedureTypeRepository = procedureTypeRepository;
    }

    public List<ProcedureType> execute() {
        return procedureTypeRepository.findAll();
    }
}
