package com.usco.pruebatecnica.filingprocedures.infrastructure.persistence;

import com.usco.pruebatecnica.filingprocedures.domain.Attachment;
import com.usco.pruebatecnica.filingprocedures.domain.AttachmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaAttachmentRepository implements AttachmentRepository {
    private final SpringDataAttachmentRepository repository;

    public JpaAttachmentRepository(SpringDataAttachmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Attachment attachment) {
        repository.save(AttachmentMapper.toEntity(attachment));
    }

    @Override
    public List<Attachment> findByProcedureId(Long procedureId) {
        return repository.findByProcedureId(procedureId)
                .stream()
                .map(AttachmentMapper::toDomain)
                .toList();
    }

    @Override
    public void saveAll(Long id, List<Long> documentTypeIdsAttached) {

    }

}
