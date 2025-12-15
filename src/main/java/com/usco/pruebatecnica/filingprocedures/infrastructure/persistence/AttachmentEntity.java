package com.usco.pruebatecnica.filingprocedures.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attachment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idattachment")
    private Long id;

    @Column(name = "idthrough", nullable = false)
    private Long procedureId;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    public AttachmentEntity(Long procedureId, String url) {
        this.procedureId = procedureId;
        this.url = url;
    }
}
