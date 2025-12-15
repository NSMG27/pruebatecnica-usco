package com.usco.pruebatecnica.proceduremanagement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProcedureTypeRepository extends JpaRepository<ProcedureTypeEntity, Long> {
}
