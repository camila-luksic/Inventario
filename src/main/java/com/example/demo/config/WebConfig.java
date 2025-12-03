package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Servir archivos estáticos desde la carpeta de uploads
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        
        // En Windows necesitamos file:/// con la ruta correcta
        String uploadAbsolutePath = "file:///" + uploadPath.toString().replace("\\", "/") + "/";
        
        System.out.println("=== WebConfig: Sirviendo archivos desde: " + uploadAbsolutePath);
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadAbsolutePath);
    }
}
