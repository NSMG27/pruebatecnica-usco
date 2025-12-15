package com.usco.pruebatecnica.proceduremanagement.domain;

import lombok.Getter;

@Getter
public class DocumentType {
    private Long id;
    private String name;

    public DocumentType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
