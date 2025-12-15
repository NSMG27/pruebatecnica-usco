package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "through")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcedureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idthrough")
    private Long id;

    // Usuario que radica el trámite
    @Column(name = "iduser", nullable = false)
    private Long userId;

    // Tipo de trámite
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtype_of_procedure", nullable = false)
    private ProcedureTypeEntity procedureType;

    @Column(name = "description")
    private String description;

    // Usuario funcionario que gestiona el trámite
    @Column(name = "iduservant")
    private Long userServantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 20)
    private ProcedureState state;

    @Column(name = "creationdate", nullable = false)
    private LocalDate creationDate;

    public ProcedureEntity(
            Long userId,
            ProcedureTypeEntity procedureType,
            String description,
            Long userServantId,
            ProcedureState state
    ) {
        this.userId = userId;
        this.procedureType = procedureType;
        this.description = description;
        this.userServantId = userServantId;
        this.state = state;
        this.creationDate = LocalDate.now();
    }

    void setId(Long id) {
        this.id = id;
    }

    void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
