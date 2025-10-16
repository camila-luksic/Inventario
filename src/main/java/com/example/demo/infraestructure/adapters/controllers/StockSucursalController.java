package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.StockSucursalDto;
import com.example.demo.application.services.StockSucursalServices;
import com.example.demo.domain.models.StockSucursal;
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
@RequestMapping("/api/stock")
@Tag(name = "StockSucursales")
public class StockSucursalController {
    private final StockSucursalServices stockServices;
    public StockSucursalController(StockSucursalServices stockServices){ this.stockServices = stockServices; }

    @GetMapping
    @Operation(summary = "Listar stock por sucursales", description = "Devuelve todos los registros de stock por sucursal")
    @ApiResponse(responseCode = "200", description = "Listado de stock", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockSucursalDto.class)))
    public ResponseEntity<List<StockSucursalDto>> getAll(){ return ResponseEntity.ok(stockServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener stock por id", description = "Devuelve un registro de stock por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockSucursalDto.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    public ResponseEntity<StockSucursalDto> getById(@Parameter(description = "Id del registro de stock", required = true) @PathVariable long id){ return ResponseEntity.ok(stockServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear stock por sucursal", description = "Crea un nuevo registro de stock por sucursal")
    @ApiResponse(responseCode = "200", description = "Registro creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockSucursalDto.class)))
    public ResponseEntity<StockSucursalDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Stock a crear") @RequestBody StockSucursal stock){ return ResponseEntity.ok(stockServices.save(stock)); }
}
