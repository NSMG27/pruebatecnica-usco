package com.usco.pruebatecnica.commentsandfollowupontheprocess.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tracing")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TracingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtracing")
    private Long id;

    @Column(name = "through_idthrough", nullable = false)
    private Long procedureId;

    @Column(name = "user_iduser", nullable = false)
    private Long userId;

    @Column(name = "comment", nullable = false, length = 500)
    private String comment;

    @Column(name = "creationdate", nullable = false)
    private LocalDate creationDate;

    public TracingEntity(Long procedureId, Long userId, String comment, LocalDate creationDate) {
        this.procedureId = procedureId;
        this.userId = userId;
        this.comment = comment;
        this.creationDate = creationDate;
    }
}
