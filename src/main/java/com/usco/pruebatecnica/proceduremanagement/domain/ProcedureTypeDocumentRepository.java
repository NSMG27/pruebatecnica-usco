package com.usco.pruebatecnica.proceduremanagement.domain;

import java.util.List;

public interface ProcedureTypeDocumentRepository {
    void save(Long procedureTypeId, Long documentTypeId);

    List<DocumentType> findDocumentTypesByProcedureType(Long procedureTypeId);
}
