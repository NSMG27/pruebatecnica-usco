package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_of_procedure_has_document_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcedureTypeDocumentEntity {
    @EmbeddedId
    private ProcedureTypeDocumentId id;

    public ProcedureTypeDocumentEntity(Long procedureTypeId, Long documentTypeId) {
        this.id = new ProcedureTypeDocumentId(procedureTypeId, documentTypeId);
    }
}
