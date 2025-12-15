package com.usco.pruebatecnica.commentsandfollowupontheprocess.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataTracingRepository extends JpaRepository<TracingEntity, Long> {
    List<TracingEntity> findByProcedureId(Long procedureId);
}
