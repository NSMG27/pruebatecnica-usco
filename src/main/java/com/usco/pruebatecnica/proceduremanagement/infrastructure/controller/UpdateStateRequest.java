package com.usco.pruebatecnica.proceduremanagement.infrastructure.controller;

import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureState;

public record UpdateStateRequest(ProcedureState state) {
}
