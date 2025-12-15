package com.usco.pruebatecnica.proceduremanagement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // Requiere JPA
@AllArgsConstructor
public class Procedure {
    private Long id;
    private Long userId;
    private ProcedureType procedureType;
    private String description;
    private Long userServantId;
    private ProcedureState state;
    private LocalDate creationDate;

    public Procedure(
            Long id,
            Long userId,
            ProcedureType procedureType,
            String description,
            Long userServantId,
            ProcedureState state
    ) {
        this.id = id;
        this.userId = userId;
        this.procedureType = procedureType;
        this.description = description;
        this.userServantId = userServantId;
        this.state = state;
        this.creationDate = LocalDate.now();
    }

    public void assignTo(Long userServantId) {
        if (this.userServantId != null) {
            throw new IllegalStateException("El trámite ya está asignado");
        }
        this.userServantId = userServantId;
    }

    public void changeState(ProcedureState newState, Long servantId) {

        if (this.userServantId == null) {
            throw new IllegalStateException("El trámite no está asignado");
        }

        if (!this.userServantId.equals(servantId)) {
            throw new IllegalStateException("El funcionario no tiene permiso para este trámite");
        }

        this.state = newState;
    }
}