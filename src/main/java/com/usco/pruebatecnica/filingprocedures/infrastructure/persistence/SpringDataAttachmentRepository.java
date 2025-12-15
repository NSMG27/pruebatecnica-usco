package com.usco.pruebatecnica.filingprocedures.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataAttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    List<AttachmentEntity> findByProcedureId(Long procedureId);
}
