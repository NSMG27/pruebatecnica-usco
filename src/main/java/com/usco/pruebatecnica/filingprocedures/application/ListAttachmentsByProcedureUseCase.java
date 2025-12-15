package com.usco.pruebatecnica.filingprocedures.application;

import com.usco.pruebatecnica.filingprocedures.domain.Attachment;
import com.usco.pruebatecnica.filingprocedures.domain.AttachmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAttachmentsByProcedureUseCase {
    private final AttachmentRepository repository;

    public ListAttachmentsByProcedureUseCase(AttachmentRepository repository) {
        this.repository = repository;
    }

    public List<Attachment> execute(Long procedureId) {
        return repository.findByProcedureId(procedureId);
    }
}
