package com.usco.pruebatecnica.proceduremanagement.infrastructure.controller;

import com.usco.pruebatecnica.authentication.domain.User;
import com.usco.pruebatecnica.authentication.domain.UserRepository;
import com.usco.pruebatecnica.proceduremanagement.application.*;
import com.usco.pruebatecnica.proceduremanagement.domain.Procedure;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureType;
import com.usco.pruebatecnica.proceduremanagement.domain.ProcedureTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tramites")
public class ProcedureController {
    private final CreateProcedureUseCase createProcedureUseCase;
    private final ListProceduresUseCase listProceduresUseCase;
    private final ListProcedureTypesUseCase listProcedureTypesUseCase;
    private final UserRepository userRepository;
    private final ListFiledProceduresUseCase listFiledProceduresUseCase;
    private final ProcedureTypeRepository procedureTypeRepository;

    // 👉 estos use cases deberías tener (o crear)
    private final AssignProcedureUseCase assignProcedureUseCase;
    private final UpdateProcedureStateUseCase updateProcedureStateUseCase;

    public ProcedureController(
            CreateProcedureUseCase createProcedureUseCase,
            ListProceduresUseCase listProceduresUseCase,
            ListProcedureTypesUseCase listProcedureTypesUseCase,
            UserRepository userRepository,
            ListFiledProceduresUseCase listFiledProceduresUseCase,
            ProcedureTypeRepository procedureTypeRepository,
            AssignProcedureUseCase assignProcedureUseCase,
            UpdateProcedureStateUseCase updateProcedureStateUseCase

    ) {
        this.createProcedureUseCase = createProcedureUseCase;
        this.listProceduresUseCase = listProceduresUseCase;
        this.listProcedureTypesUseCase = listProcedureTypesUseCase;
        this.userRepository = userRepository;
        this.listFiledProceduresUseCase = listFiledProceduresUseCase;
        this.procedureTypeRepository = procedureTypeRepository;
        this.assignProcedureUseCase = assignProcedureUseCase;
        this.updateProcedureStateUseCase = updateProcedureStateUseCase;
    }

    // ---------------- CREATE PROCEDURE ----------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProcedureRequest request, Authentication auth) {
        try {
            String email = auth.getName();

            Long userId = userRepository.findByEmail(email)
                    .map(User::getId)
                    .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

            ProcedureType procedureType =
                    procedureTypeRepository.findById(request.procedureTypeId())
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Tipo de trámite no válido")
                            );

            createProcedureUseCase.execute(
                    userId,                       // ✅ viene del token (email → id)
                    procedureType,
                    request.description(),
                    request.userServantId(),
                    request.documentTypeIds()
            );

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalStateException | IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    // 2️⃣ Asignar trámite
    @PutMapping("/{id}/asignar")
    public ResponseEntity<?> assignProcedure(
            @PathVariable Long id,
            @RequestBody AssignProcedureRequest request
    ) {
        assignProcedureUseCase.execute(id, request.userServantId());
        return ResponseEntity.ok().build();
    }

    // 3️⃣ Actualizar estado
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMINISTRATIVE_STAFF')")
    public ResponseEntity<?> updateState(
            @PathVariable Long id,
            @RequestBody UpdateStateRequest request,
            Authentication authentication
    ) {
        try {
            String email = authentication.getName();

            Long servantId = userRepository.findByEmail(email)
                    .map(User::getId)
                    .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

            updateProcedureStateUseCase.execute(
                    id,
                    request.state(),
                    servantId
            );

            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException ex) {
            // Ej: request inválido, estado inválido, etc.
            // Si usas "Trámite no encontrado", es más correcto 404:
            if ("Trámite no encontrado".equalsIgnoreCase(ex.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", ex.getMessage()));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));

        } catch (IllegalStateException ex) {
            // Regla de negocio / permisos
            String msg = ex.getMessage() == null ? "Operación no permitida" : ex.getMessage();

            if (msg.toLowerCase().contains("permiso") || msg.toLowerCase().contains("no está asignado")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error", msg));
            }

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", msg));
        }
    }

    // 🔹 LISTAR TRÁMITES RADICADOS
    @GetMapping("/funcionario/{id}")
    @PreAuthorize("hasRole('ADMINISTRATIVE_STAFF')")
    public ResponseEntity<?> listFiled(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            String email = authentication.getName();

            Long authenticatedId = userRepository.findByEmail(email)
                    .map(User::getId)
                    .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado"));

            if (!authenticatedId.equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error", "No tienes permiso para consultar los trámites de otro funcionario"));
            }

            List<Procedure> result = listFiledProceduresUseCase.execute(id);
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    // 4️⃣ Agregar comentario
    /*@PostMapping("/{id}/comentarios")
    public ResponseEntity<?> addComment(
            @PathVariable Long id,
            @RequestBody AddCommentRequest request
    ) {
        addCommentUseCase.execute(id, request.userId(), request.comment());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 5️⃣ Listar trámites de un funcionario
    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<Procedure>> listByServant(@PathVariable Long id) {
        return ResponseEntity.ok(
                listByServantUseCase.execute(id)
        );
    }

    // 6️⃣ Ver seguimiento del trámite
    @GetMapping("/{id}/seguimiento")
    public ResponseEntity<?> tracking(@PathVariable Long id) {
        return ResponseEntity.ok(
                getProcedureTrackingUseCase.execute(id)
        );
    }*/

    // ---------------- LIST PROCEDURES ----------------
    @GetMapping
    public ResponseEntity<List<Procedure>> list() {
        return ResponseEntity.ok(
                listProceduresUseCase.execute()
        );
    }

    // ---------------- LIST PROCEDURE TYPES ----------------
    @GetMapping("/types")
    public ResponseEntity<List<ProcedureType>> listTypes() {
        return ResponseEntity.ok(
                listProcedureTypesUseCase.execute()
        );
    }
}
