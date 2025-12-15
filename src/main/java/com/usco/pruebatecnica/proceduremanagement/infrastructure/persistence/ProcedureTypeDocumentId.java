package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProcedureTypeDocumentId implements Serializable {
    @Column(name = "type_of_procedure_idtype_of_procedure")
    private Long procedureTypeId;

    @Column(name = "document_type_iddocument_type")
    private Long documentTypeId;
}
