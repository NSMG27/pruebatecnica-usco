package com.usco.pruebatecnica.filingprocedures.infrastructure.controller;

import com.usco.pruebatecnica.filingprocedures.application.AddAttachmentUseCase;
import com.usco.pruebatecnica.filingprocedures.application.ListAttachmentsByProcedureUseCase;
import com.usco.pruebatecnica.filingprocedures.domain.Attachment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramites")
public class AttachmentController {
    private final AddAttachmentUseCase addAttachmentUseCase;
    private final ListAttachmentsByProcedureUseCase listAttachmentsUseCase;

    public AttachmentController(AddAttachmentUseCase addAttachmentUseCase,
                                ListAttachmentsByProcedureUseCase listAttachmentsUseCase) {
        this.addAttachmentUseCase = addAttachmentUseCase;
        this.listAttachmentsUseCase = listAttachmentsUseCase;
    }

    // 🔹 Agregar archivo
    @PostMapping("/{id}/archivos")
    public ResponseEntity<?> addAttachment(
            @PathVariable Long id,
            @RequestBody AddAttachmentRequest request
    ) {
        addAttachmentUseCase.execute(id, request.url());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 🔹 Listar archivos
    @GetMapping("/{id}/archivos")
    public ResponseEntity<List<Attachment>> listAttachments(@PathVariable Long id) {
        return ResponseEntity.ok(
                listAttachmentsUseCase.execute(id)
        );
    }
}

record AddAttachmentRequest(String url) {}