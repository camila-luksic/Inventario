package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aplica la configuración CORS a todos los paths (/**)
        registry.addMapping("/**")
                // Permite solicitudes desde este origen (ej. tu frontend)
                // Es mejor especificar el dominio exacto en lugar de "*"
                .allowedOrigins("http://localhost:5173")
                // Define los métodos HTTP permitidos (GET, POST, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                // Permite el envío de cookies o credenciales (si es necesario)
                .allowCredentials(true)
                // Define los encabezados permitidos
                .allowedHeaders("*");
    }
}