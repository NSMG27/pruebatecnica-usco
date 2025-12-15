package com.usco.pruebatecnica.proceduremanagement.domain;

import lombok.Getter;

@Getter
public class ProcedureTypeDocumentRequirement {
    private Long procedureTypeId;
    private Long documentTypeId;

    public ProcedureTypeDocumentRequirement(Long procedureTypeId,
                                            Long documentTypeId) {
        this.procedureTypeId = procedureTypeId;
        this.documentTypeId = documentTypeId;
    }
}
