package com.example.demo.infraestructure.adapters.controllers;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.application.services.FileUploadService;
import com.example.demo.application.services.MarcaServices;
import com.example.demo.domain.models.Marca;
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
@RequestMapping("/api/marcas")
@Tag(name = "Marcas")
public class MarcaController {
    private final MarcaServices marcaServices;
    private final FileUploadService fileUploadService;

    public MarcaController(MarcaServices marcaServices, FileUploadService fileUploadService) {
        this.marcaServices = marcaServices;
        this.fileUploadService = fileUploadService;
    }

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Crear marca con imagen", description = "Crea una nueva marca con imagen")
    @ApiResponse(responseCode = "200", description = "Marca creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDto.class)))
    public ResponseEntity<MarcaDto> saveWithImage(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        
        System.out.println("=== CREAR MARCA ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("File recibido: " + (file != null ? file.getOriginalFilename() : "NULL"));
        System.out.println("File isEmpty: " + (file != null ? file.isEmpty() : "N/A"));
        
        String fotoUrl = null;
        if (file != null && !file.isEmpty()) {
            fotoUrl = fileUploadService.saveFile(file, "marcas");
            System.out.println("Foto URL guardada: " + fotoUrl);
        } else {
            System.out.println("No se recibió archivo de imagen");
        }
        
        Marca marca = new Marca(0, nombre, fotoUrl);
        System.out.println("Marca a guardar - nombre: " + marca.getNombre() + ", foto: " + marca.getFoto());
        
        MarcaDto resultado = marcaServices.save(marca);
        System.out.println("Marca guardada - id: " + resultado.getId() + ", foto: " + resultado.getFoto());
        System.out.println("===================");
        
        return ResponseEntity.ok(resultado);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear marca", description = "Crea una nueva marca (JSON)")
    public ResponseEntity<MarcaDto> save(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaServices.save(marca));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Actualizar marca con imagen", description = "Actualiza una marca existente con imagen")
    public ResponseEntity<MarcaDto> updateWithImage(
            @PathVariable long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        
        String fotoUrl = null;
        if (file != null && !file.isEmpty()) {
            fotoUrl = fileUploadService.saveFile(file, "marcas");
        }
        
        Marca marca = new Marca(id, nombre, fotoUrl);
        return ResponseEntity.ok(marcaServices.update(id, marca));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Actualizar marca", description = "Actualiza una marca existente (JSON)")
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
