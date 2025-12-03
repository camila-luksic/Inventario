package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.ProductoDto;
import com.example.demo.application.services.FileUploadService;
import com.example.demo.application.services.ProductoServices;
import com.example.demo.domain.models.Producto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos")
public class ProductoController {
    private final ProductoServices productoServices;
    private final FileUploadService fileUploadService;

    public ProductoController(ProductoServices productoServices, FileUploadService fileUploadService) {
        this.productoServices = productoServices;
        this.fileUploadService = fileUploadService;
    }

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Crear producto con imagen", description = "Crea un nuevo producto con imagen")
    @ApiResponse(responseCode = "200", description = "Producto creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDto.class)))
    public ResponseEntity<ProductoDto> saveWithImage(
            @RequestParam("nombre") String nombre,
            @RequestParam("marcaId") Long marcaId,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "activo", defaultValue = "true") boolean activo,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        
        System.out.println("=== CREAR PRODUCTO ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("MarcaId: " + marcaId);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Activo: " + activo);
        System.out.println("File recibido: " + (file != null ? file.getOriginalFilename() : "NULL"));
        System.out.println("File isEmpty: " + (file != null ? file.isEmpty() : "N/A"));
        
        String fotoUrl = null;
        if (file != null && !file.isEmpty()) {
            fotoUrl = fileUploadService.saveFile(file, "productos");
            System.out.println("Foto URL guardada: " + fotoUrl);
        } else {
            System.out.println("No se recibió archivo de imagen");
        }
        
        ProductoDto dto = new ProductoDto(null, nombre, marcaId, descripcion, activo, fotoUrl);
        System.out.println("Producto a guardar - nombre: " + dto.getNombre() + ", foto: " + dto.getFoto());
        
        ProductoDto resultado = productoServices.save(dto);
        System.out.println("Producto guardado - id: " + resultado.getId() + ", foto: " + resultado.getFoto());
        System.out.println("======================");
        
        return ResponseEntity.ok(resultado);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto (JSON)")
    public ResponseEntity<ProductoDto> save(@RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoServices.save(productoDto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Actualizar producto con imagen", description = "Actualiza un producto existente con imagen")
    public ResponseEntity<ProductoDto> updateWithImage(
            @PathVariable long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("marcaId") Long marcaId,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "activo", defaultValue = "true") boolean activo,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        
        String fotoUrl = null;
        if (file != null && !file.isEmpty()) {
            fotoUrl = fileUploadService.saveFile(file, "productos");
        }
        
        ProductoDto dto = new ProductoDto(id, nombre, marcaId, descripcion, activo, fotoUrl);
        return ResponseEntity.ok(productoServices.update(id, dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente (JSON)")
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
