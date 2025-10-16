package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.ProductoDto;
import com.example.demo.application.services.ProductoServices;
import com.example.demo.domain.models.Producto;
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
@RequestMapping("/api/productos")
@Tag(name = "Productos")
public class ProductoController {
    private final ProductoServices productoServices;
    public ProductoController(ProductoServices productoServices){ this.productoServices = productoServices; }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Devuelve todos los productos")
    @ApiResponse(responseCode = "200", description = "Listado de productos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDto.class)))
    public ResponseEntity<List<ProductoDto>> getAll(){ return ResponseEntity.ok(productoServices.getAll()); }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por id", description = "Devuelve un producto por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoDto> getById(@Parameter(description = "Id del producto", required = true) @PathVariable long id){ return ResponseEntity.ok(productoServices.getById(id)); }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDto.class)))
    public ResponseEntity<ProductoDto> save(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a crear") @RequestBody ProductoDto productoDto){ return ResponseEntity.ok(productoServices.save(productoDto)); }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente")
    public ResponseEntity<ProductoDto> update(@PathVariable long id, @RequestBody ProductoDto productoDto){
        return ResponseEntity.ok(productoServices.update(id, productoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por id")
    public ResponseEntity<Void> delete(@PathVariable long id){
        productoServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
