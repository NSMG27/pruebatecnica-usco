package com.usco.pruebatecnica.filingprocedures.application;

import com.usco.pruebatecnica.filingprocedures.domain.Attachment;
import com.usco.pruebatecnica.filingprocedures.domain.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddAttachmentUseCase {
    private final AttachmentRepository repository;

    public AddAttachmentUseCase(AttachmentRepository repository) {
        this.repository = repository;
    }

    public void execute(Long procedureId, String url) {

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL del archivo es obligatoria");
        }

        Attachment attachment = new Attachment(procedureId, url);
        repository.save(attachment);
    }
}
