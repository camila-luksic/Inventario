package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.application.services.MovimientoInventarioServices;
import com.example.demo.domain.models.MovimientoInventario;
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
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos")
public class MovimientoInventarioController {
    private final MovimientoInventarioServices movimientoServices;
    public MovimientoInventarioController(MovimientoInventarioServices movimientoServices){ this.movimientoServices = movimientoServices; }

    @GetMapping
    @Operation(summary = "Listar movimientos", description = "Devuelve todos los movimientos de inventario")
    @ApiResponse(responseCode = "200", description = "Listado de movimientos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoInventarioDto.class)))
    public ResponseEntity<List<MovimientoInventarioDto>> getAll(){ return ResponseEntity.ok(movimientoServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener movimiento por id", description = "Devuelve un movimiento por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoInventarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    public ResponseEntity<MovimientoInventarioDto> getById(@Parameter(description = "Id del movimiento", required = true) @PathVariable long id){ return ResponseEntity.ok(movimientoServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear movimiento", description = "Registra un nuevo movimiento de inventario")
    @ApiResponse(responseCode = "200", description = "Movimiento creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoInventarioDto.class)))
    public ResponseEntity<MovimientoInventarioDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Movimiento a crear") @RequestBody MovimientoInventario movimiento){ return ResponseEntity.ok(movimientoServices.save(movimiento)); }
}
