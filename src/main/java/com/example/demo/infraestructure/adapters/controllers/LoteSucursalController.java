package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.application.services.LoteSucursalServices;
import com.example.demo.domain.models.LoteSucursal;
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
@RequestMapping("/api/lote-sucursales")
@Tag(name = "LoteSucursales")
public class LoteSucursalController {
    private final LoteSucursalServices loteSucursalServices;
    public LoteSucursalController(LoteSucursalServices loteSucursalServices){ this.loteSucursalServices = loteSucursalServices; }

    @GetMapping
    @Operation(summary = "Listar lote-sucursales", description = "Devuelve todos los registros lote-sucursal")
    @ApiResponse(responseCode = "200", description = "Listado lote-sucursal", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteSucursalDto.class)))
    public ResponseEntity<List<LoteSucursalDto>> getAll(){ return ResponseEntity.ok(loteSucursalServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener lote-sucursal por id", description = "Devuelve un registro lote-sucursal por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteSucursalDto.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<LoteSucursalDto> getById(@Parameter(description = "Id del registro", required = true) @PathVariable long id){ return ResponseEntity.ok(loteSucursalServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear lote-sucursal", description = "Crea un nuevo registro lote-sucursal")
    @ApiResponse(responseCode = "200", description = "Registro creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoteSucursalDto.class)))
    public ResponseEntity<LoteSucursalDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Registro a crear") @RequestBody LoteSucursal loteSucursal){ return ResponseEntity.ok(loteSucursalServices.save(loteSucursal)); }
}
