package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.DocumentType;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureTypeDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProcedureTypeDocumentRepository implements ProcedureTypeDocumentRepository {
    private final SpringDataProcedureTypeDocumentRepository repository;

    public JpaProcedureTypeDocumentRepository(
            SpringDataProcedureTypeDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Long procedureTypeId, Long documentTypeId) {
        repository.save(
                new ProcedureTypeDocumentEntity(procedureTypeId, documentTypeId)
        );
    }

    @Override
    public List<DocumentType> findDocumentTypesByProcedureType(Long procedureTypeId) {
        return repository.findDocumentTypesByProcedureTypeId(procedureTypeId)
                .stream()
                .map(e -> new DocumentType(e.getId(), e.getName()))
                .toList();
    }
}
