package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.UsuarioDto;
import com.example.demo.application.port.out.UsuarioRepositoryPort;
import com.example.demo.domain.models.Usuario;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.UsuarioJpaRepository;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.UsuarioMapper;
import com.example.demo.infraestructure.adapters.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDto> getAll() {
        return usuarioMapper.ofEntityToDto(usuarioRepository.findAll());
    }

    @Override
    public UsuarioDto getById(long id) {
        return usuarioMapper.ofEntityToDto(usuarioRepository.findById(id).orElse(null));
    }

    @Override
    public Optional<UsuarioDto> findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(usuarioMapper::ofEntityToDto);
    }

    @Override
    public Optional<UsuarioDto> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::ofEntityToDto);
    }

    @Override
    public UsuarioDto save(Usuario usuario) {
        if (usuario.getId() > 0) {
            UsuarioEntity existing = usuarioRepository.findById(usuario.getId()).orElse(null);
            if (existing != null) {
                existing.setUsername(usuario.getUsername());
                existing.setPassword(usuario.getPassword());
                existing.setEmail(usuario.getEmail());
                existing.setRol(usuario.getRol());
                existing.setActivo(usuario.isActivo());
                return usuarioMapper.ofEntityToDto(usuarioRepository.save(existing));
            }
        }
        UsuarioEntity entity = usuarioMapper.ofModelToEntity(usuario);
        return usuarioMapper.ofEntityToDto(usuarioRepository.save(entity));
    }

    @Override
    public void deleteById(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
