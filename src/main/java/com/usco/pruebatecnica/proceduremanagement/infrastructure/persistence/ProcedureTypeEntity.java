package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "type_of_procedure")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "procedures")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProcedureTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtype_of_procedure")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Relación con trámites (through)
    @OneToMany(mappedBy = "procedureType", fetch = FetchType.LAZY)
    private Set<ProcedureEntity> procedures;

    public ProcedureTypeEntity(String name) {
        this.name = name;
    }
    public ProcedureTypeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
