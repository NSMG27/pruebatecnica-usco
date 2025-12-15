package com.usco.pruebatecnica.commentsandfollowupontheprocess.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Tracing {
    private Long id;
    private Long procedureId;
    private Long userId;
    private String comment;
    private LocalDate creationDate;

    public Tracing(Long procedureId, Long userId, String comment) {
        this.procedureId = procedureId;
        this.userId = userId;
        this.comment = comment;
        this.creationDate = LocalDate.now();
    }

    public Tracing(Long id, Long procedureId, Long userId,
                   String comment, LocalDate creationDate) {
        this.id = id;
        this.procedureId = procedureId;
        this.userId = userId;
        this.comment = comment;
        this.creationDate = creationDate;
    }
}
