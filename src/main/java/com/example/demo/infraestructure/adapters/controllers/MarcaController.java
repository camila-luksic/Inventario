package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.application.services.MarcaServices;
import com.example.demo.domain.models.Marca;
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
@RequestMapping("/api/marcas")
@Tag(name = "Marcas")
public class MarcaController {
    private final MarcaServices marcaServices;
    public MarcaController(MarcaServices marcaServices){ this.marcaServices = marcaServices; }

    @GetMapping
    @Operation(summary = "Listar marcas", description = "Devuelve todas las marcas")
    @ApiResponse(responseCode = "200", description = "Listado de marcas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDto.class)))
    public ResponseEntity<List<MarcaDto>> getAll(){ return ResponseEntity.ok(marcaServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener marca por id", description = "Devuelve una marca por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDto.class))),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    public ResponseEntity<MarcaDto> getById(@Parameter(description = "Id de la marca", required = true) @PathVariable long id){ return ResponseEntity.ok(marcaServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear marca", description = "Crea una nueva marca")
    @ApiResponse(responseCode = "200", description = "Marca creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDto.class)))
    public ResponseEntity<MarcaDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Marca a crear") @RequestBody Marca marca){ return ResponseEntity.ok(marcaServices.save(marca)); }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar marca", description = "Actualiza una marca existente")
    public ResponseEntity<MarcaDto> update(@PathVariable long id, @RequestBody Marca marca){
        return ResponseEntity.ok(marcaServices.update(id, marca));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar marca", description = "Elimina una marca por id")
    public ResponseEntity<Void> delete(@PathVariable long id){
        marcaServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
