package com.usco.pruebatecnica.filingprocedures.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Attachment {
    private Long id;
    private Long procedureId;
    private String url;

    public Attachment(Long procedureId, String url) {
        this.procedureId = procedureId;
        this.url = url;
    }

    public Attachment(Long id, Long procedureId, String url) {
        this.id = id;
        this.procedureId = procedureId;
        this.url = url;
    }
}
