package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddocument_type")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
