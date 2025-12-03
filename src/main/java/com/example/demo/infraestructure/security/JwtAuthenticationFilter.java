package com.example.demo.infraestructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestURI = request.getRequestURI();
        final String method = request.getMethod();
        final String authHeader = request.getHeader("Authorization");

        logger.info("========== JWT FILTER ==========");
        logger.info("Method: {} | URI: {}", method, requestURI);
        logger.info("Authorization Header: {}", authHeader != null ? authHeader.substring(0, Math.min(50, authHeader.length())) + "..." : "NULL");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("No Bearer token found, continuing without authentication");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            logger.info("Token extracted (first 50 chars): {}...", jwt.substring(0, Math.min(50, jwt.length())));
            
            final String username = jwtService.extractUsername(jwt);
            logger.info("Username extracted: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                boolean isValid = jwtService.validateToken(jwt, username);
                logger.info("Token validation result: {}", isValid);
                
                if (isValid) {
                    String rol = jwtService.extractRol(jwt);
                    logger.info("Role extracted: {}", rol);
                    
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol))
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("Authentication set successfully for user: {}", username);
                } else {
                    logger.warn("Token validation FAILED for user: {}", username);
                }
            } else {
                logger.info("Username is null or authentication already exists");
            }
        } catch (Exception e) {
            logger.error("Error processing JWT token: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }

        logger.info("Current Authentication: {}", SecurityContextHolder.getContext().getAuthentication());
        logger.info("================================");
        
        filterChain.doFilter(request, response);
    }
}
