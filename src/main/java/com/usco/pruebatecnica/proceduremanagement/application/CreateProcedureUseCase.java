package com.usco.pruebatecnica.proceduremanagement.application;

import com.usco.pruebatecnica.filingprocedures.domain.AttachmentRepository;
import com.usco.pruebatecnica.proceduremanagement.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateProcedureUseCase {
    private final ProcedureRepository procedureRepository;
    private final ProcedureTypeDocumentRepository requirementRepository;
    private final AttachmentRepository attachmentRepository;

    public CreateProcedureUseCase(ProcedureRepository procedureRepository, ProcedureTypeDocumentRepository requirementRepository, AttachmentRepository attachmentRepository) {
        this.procedureRepository = procedureRepository;
        this.requirementRepository = requirementRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public void execute(Long userId,
                        ProcedureType procedureType,
                        String description,
                        Long userServantId,
                        List<Long> documentTypeIdsAttached) {
        // 1️⃣ Obtener documentos requeridos por el tipo de trámite
        List<DocumentType> requiredDocuments =
                requirementRepository.findDocumentTypesByProcedureType(
                        procedureType.getId()
                );

        // 2️⃣ Validar que estén todos los obligatorios
        List<Long> missingDocuments = requiredDocuments.stream()
                .map(DocumentType::getId)
                .filter(requiredId ->
                        !documentTypeIdsAttached.contains(requiredId)
                )
                .toList();

        if (!missingDocuments.isEmpty()) {
            throw new IllegalStateException(
                    "Faltan documentos obligatorios para el tipo de trámite: "
                            + procedureType.getName()
            );
        }

        // 3️⃣ Crear el trámite
        Procedure procedure = new Procedure(
                null,
                userId,
                procedureType,
                description,
                userServantId,
                ProcedureState.FILED
        );

        procedureRepository.save(procedure);

        // 4️⃣ (Opcional pero correcto) Asociar adjuntos
        // Simulado por URLs, según tu diseño
        attachmentRepository.saveAll(
                procedure.getId(),
                documentTypeIdsAttached
        );
    }
}
