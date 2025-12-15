package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;

public class ProcedureMapper {
    private ProcedureMapper() {
        // Utility class
    }

    // ---------------- DOMAIN → ENTITY ----------------
    public static ProcedureEntity toEntity(Procedure procedure) {
        if (procedure == null) {
            return null;
        }

        ProcedureType procedureType = procedure.getProcedureType();
        ProcedureTypeEntity procedureTypeEntity = null;
        if (procedureType != null) {
            procedureTypeEntity = new ProcedureTypeEntity(
                    procedureType.getId(),
                    procedureType.getName()
            );
        }

        ProcedureEntity entity = new ProcedureEntity(
                procedure.getUserId(),
                procedureTypeEntity,
                procedure.getDescription(),
                procedure.getUserServantId(),
                procedure.getState()
        );

        // IMPORTANTÍSIMO: preservar ID para que sea UPDATE y no INSERT
        entity.setId(procedure.getId());

        // Preservar fecha si el dominio ya la trae
        if (procedure.getCreationDate() != null) {
            entity.setCreationDate(procedure.getCreationDate());
        }

        return entity;
    }

    // ---------------- ENTITY → DOMAIN ----------------
    public static Procedure toDomain(ProcedureEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Procedure(
                entity.getId(),
                entity.getUserId(),
                new ProcedureType(
                        entity.getProcedureType().getId(),
                        entity.getProcedureType().getName()
                ),
                entity.getDescription(),
                entity.getUserServantId(),
                entity.getState()
        );
    }
}
