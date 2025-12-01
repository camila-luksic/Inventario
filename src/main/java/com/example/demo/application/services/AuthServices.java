package com.example.demo.application.services;

import com.example.demo.application.dto.*;
import com.example.demo.application.port.out.UsuarioRepositoryPort;
import com.example.demo.domain.models.Usuario;
import com.example.demo.infraestructure.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthServices {
    
    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServices(UsuarioRepositoryPort usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Optional<UsuarioDto> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());
        
        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        UsuarioDto usuario = usuarioOpt.get();

        if (!usuario.isActivo()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario desactivado");
        }

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.getUsername(), usuario.getRol());

        return new LoginResponseDto(token, usuario.getUsername(), usuario.getRol());
    }

    public UsuarioDto register(RegisterRequestDto request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya está registrado");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Usuario usuario = new Usuario(
                0,
                request.getUsername(),
                encodedPassword,
                request.getEmail(),
                "USER",
                true
        );

        return usuarioRepository.save(usuario);
    }

    public UsuarioDto getCurrentUser(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }
}
