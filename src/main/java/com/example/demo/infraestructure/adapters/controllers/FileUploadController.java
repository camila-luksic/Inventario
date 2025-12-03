package com.example.demo.infraestructure.adapters.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
@Tag(name = "Uploads", description = "Endpoints para subir archivos e imágenes")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Value("${server.port:8081}")
    private String serverPort;

    @PostMapping(value = "/imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen", description = "Sube una imagen y devuelve la URL para usarla en productos o marcas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Archivo inválido o vacío"),
            @ApiResponse(responseCode = "500", description = "Error al guardar el archivo")
    })
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "tipo", defaultValue = "general") String tipo) {

        logger.info("Recibiendo archivo: {} | Tipo: {} | Tamaño: {} bytes", 
                file.getOriginalFilename(), tipo, file.getSize());

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El archivo está vacío"));
        }

        // Validar que sea una imagen
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.badRequest().body(Map.of("error", "El archivo debe ser una imagen"));
        }

        try {
            // Crear directorio si no existe
            Path uploadPath = Paths.get(uploadDir, tipo);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                logger.info("Directorio creado: {}", uploadPath);
            }

            // Generar nombre único para el archivo
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = UUID.randomUUID().toString() + extension;

            // Guardar el archivo
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Archivo guardado en: {}", filePath);

            // Construir la URL de acceso
            String fileUrl = "/uploads/" + tipo + "/" + newFilename;

            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            response.put("filename", newFilename);
            response.put("originalName", originalFilename);
            response.put("size", String.valueOf(file.getSize()));

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            logger.error("Error al guardar archivo: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al guardar el archivo: " + e.getMessage()));
        }
    }

    @PostMapping(value = "/imagen/marca", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen de marca", description = "Sube una imagen para una marca")
    public ResponseEntity<Map<String, String>> uploadMarcaImage(@RequestParam("file") MultipartFile file) {
        return uploadImage(file, "marcas");
    }

    @PostMapping(value = "/imagen/producto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen de producto", description = "Sube una imagen para un producto")
    public ResponseEntity<Map<String, String>> uploadProductoImage(@RequestParam("file") MultipartFile file) {
        return uploadImage(file, "productos");
    }
}
