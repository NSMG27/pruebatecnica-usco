package com.usco.pruebatecnica.filingprocedures.infrastructure.persistence;

import com.usco.pruebatecnica.filingprocedures.domain.Attachment;

public class AttachmentMapper {
    public static AttachmentEntity toEntity(Attachment domain) {
        return new AttachmentEntity(domain.getProcedureId(), domain.getUrl());
    }

    public static Attachment toDomain(AttachmentEntity entity) {
        return new Attachment(
                entity.getId(),
                entity.getProcedureId(),
                entity.getUrl()
        );
    }
}
