package com.usco.pruebatecnica.filingprocedures.domain;

import java.util.List;

public interface AttachmentRepository {
    void save(Attachment attachment);

    List<Attachment> findByProcedureId(Long procedureId);

    void saveAll(Long id, List<Long> documentTypeIdsAttached);
}
