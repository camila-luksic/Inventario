package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.SucursalDto;
import com.example.demo.application.services.SucursalServices;
import com.example.demo.domain.models.Sucursal;
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
@RequestMapping("/api/sucursales")
@Tag(name = "Sucursales")
public class SucursalController {
    private final SucursalServices sucursalServices;
    public SucursalController(SucursalServices sucursalServices){ this.sucursalServices = sucursalServices; }

    @GetMapping
    @Operation(summary = "Listar sucursales", description = "Devuelve todas las sucursales")
    @ApiResponse(responseCode = "200", description = "Listado de sucursales", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SucursalDto.class)))
    public ResponseEntity<List<SucursalDto>> getAll(){ return ResponseEntity.ok(sucursalServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sucursal por id", description = "Devuelve una sucursal por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SucursalDto.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<SucursalDto> getById(@Parameter(description = "Id de la sucursal", required = true) @PathVariable long id){ return ResponseEntity.ok(sucursalServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear sucursal", description = "Crea una nueva sucursal")
    @ApiResponse(responseCode = "200", description = "Sucursal creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SucursalDto.class)))
    public ResponseEntity<SucursalDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Sucursal a crear") @RequestBody Sucursal sucursal){ return ResponseEntity.ok(sucursalServices.save(sucursal)); }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar sucursal", description = "Actualiza una sucursal existente")
    public ResponseEntity<SucursalDto> update(@PathVariable long id, @RequestBody Sucursal sucursal){
        return ResponseEntity.ok(sucursalServices.update(id, sucursal));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal por id")
    public ResponseEntity<Void> delete(@PathVariable long id){
        sucursalServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
