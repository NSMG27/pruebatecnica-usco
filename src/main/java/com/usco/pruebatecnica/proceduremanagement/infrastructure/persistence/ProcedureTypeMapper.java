package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;

public class ProcedureTypeMapper {
    private ProcedureTypeMapper() {}

    public static ProcedureType toDomain(ProcedureTypeEntity entity) {
        return new ProcedureType(
                entity.getId(),
                entity.getName()
        );
    }
}
