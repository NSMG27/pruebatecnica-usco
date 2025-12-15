package com.usco.pruebatecnica.commentsandfollowupontheprocess.domain;

import java.util.List;

public interface TracingRepository {
    void save(Tracing tracing);

    List<Tracing> findByProcedureId(Long procedureId);
}
