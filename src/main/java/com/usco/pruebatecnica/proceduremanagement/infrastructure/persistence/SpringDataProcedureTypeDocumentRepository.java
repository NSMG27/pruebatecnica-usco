package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataProcedureTypeDocumentRepository extends JpaRepository<ProcedureTypeDocumentEntity, ProcedureTypeDocumentId> {
    @Query("""
        SELECT d
        FROM DocumentTypeEntity d
        JOIN ProcedureTypeDocumentEntity p
          ON d.id = p.id.documentTypeId
        WHERE p.id.procedureTypeId = :procedureTypeId
    """)
    List<DocumentTypeEntity> findDocumentTypesByProcedureTypeId(Long procedureTypeId);
}
