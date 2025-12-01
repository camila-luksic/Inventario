package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.LoteDto;
import com.example.demo.application.services.LoteServices;
import com.example.demo.domain.models.Lote;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@Tag(name = "Lotes")
public class LoteController {
    private final LoteServices loteServices;
    public LoteController(LoteServices loteServices){ this.loteServices = loteServices; }

    @GetMapping
    @Operation(summary = "Listar lotes", description = "Devuelve todos los lotes")
    @ApiResponse(responseCode = "200", description = "Listado de lotes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteDto.class)))
    public ResponseEntity<List<LoteDto>> getAll(){ return ResponseEntity.ok(loteServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener lote por id", description = "Devuelve un lote por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lote encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteDto.class))),
            @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    })
    public ResponseEntity<LoteDto> getById(@Parameter(description = "Id del lote", required = true) @PathVariable long id){ return ResponseEntity.ok(loteServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear lote", description = "Crea un nuevo lote")
    @ApiResponse(responseCode = "200", description = "Lote creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteDto.class)))
    public ResponseEntity<LoteDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Lote a crear") @RequestBody LoteDto lote){ return ResponseEntity.ok(loteServices.save(lote)); }

    @PatchMapping("/{id}/toggle-notificacion")
    @Operation(summary = "Activar/Desactivar notificación de lote", description = "Habilita o deshabilita la notificación para un lote")
    public ResponseEntity<LoteDto> toggleNotification(@PathVariable long id, @RequestParam boolean enabled){
        return ResponseEntity.ok(loteServices.toggleNotification(id, enabled));
    }

    @PostMapping("/dar-de-baja/{id}")
    @Operation(summary = "Dar de baja", description = "Marca un lote como inactivo")
    @ApiResponse(responseCode = "200", description = "Lote dado de baja", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteDto.class)))
    public ResponseEntity<LoteDto> darDeBaja(@PathVariable long id){ return ResponseEntity.ok(loteServices.darDeBaja(id)); }
}
