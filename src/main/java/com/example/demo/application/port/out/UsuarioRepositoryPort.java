package com.example.demo.application.port.out;

import com.example.demo.application.dto.UsuarioDto;
import com.example.demo.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    List<UsuarioDto> getAll();
    UsuarioDto getById(long id);
    Optional<UsuarioDto> findByUsername(String username);
    Optional<UsuarioDto> findByEmail(String email);
    UsuarioDto save(Usuario usuario);
    void deleteById(long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
