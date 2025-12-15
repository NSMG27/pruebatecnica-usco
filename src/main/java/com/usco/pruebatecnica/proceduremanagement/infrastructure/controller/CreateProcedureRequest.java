package com.usco.pruebatecnica.proceduremanagement.infrastructure.controller;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;

import java.util.List;

public record CreateProcedureRequest(Long procedureTypeId, String description, Long userServantId, List<Long> documentTypeIds) {
}
