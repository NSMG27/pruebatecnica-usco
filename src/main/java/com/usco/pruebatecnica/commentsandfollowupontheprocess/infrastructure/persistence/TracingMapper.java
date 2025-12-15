package com.usco.pruebatecnica.commentsandfollowupontheprocess.infrastructure.persistence;

import com.usco.pruebatecnica.commentsandfollowupontheprocess.domain.Tracing;

public class TracingMapper {
    public static TracingEntity toEntity(Tracing domain) {
        return new TracingEntity(
                domain.getProcedureId(),
                domain.getUserId(),
                domain.getComment(),
                domain.getCreationDate()
        );
    }

    public static Tracing toDomain(TracingEntity entity) {
        return new Tracing(
                entity.getId(),
                entity.getProcedureId(),
                entity.getUserId(),
                entity.getComment(),
                entity.getCreationDate()
        );
    }
}
