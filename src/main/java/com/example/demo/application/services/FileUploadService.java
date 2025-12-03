package com.example.demo.application.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    /**
     * Guarda un archivo y devuelve la URL relativa
     */
    public String saveFile(MultipartFile file, String tipo) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // Validar que sea una imagen
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen");
        }

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

        // Devolver la URL relativa
        return "/uploads/" + tipo + "/" + newFilename;
    }

    /**
     * Elimina un archivo por su URL
     */
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return;
        }

        try {
            // Convertir URL a path: /uploads/marcas/xxx.jpg -> uploads/marcas/xxx.jpg
            String relativePath = fileUrl.startsWith("/") ? fileUrl.substring(1) : fileUrl;
            Path filePath = Paths.get(relativePath);
            
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                logger.info("Archivo eliminado: {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error al eliminar archivo: {}", e.getMessage());
        }
    }
}
