package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.UsuarioDto;
import com.example.demo.domain.models.Usuario;
import com.example.demo.infraestructure.adapters.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioDto ofEntityToDto(UsuarioEntity entity) {
        if (entity == null) return null;
        return new UsuarioDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getRol(),
                entity.isActivo()
        );
    }

    public List<UsuarioDto> ofEntityToDto(List<UsuarioEntity> entities) {
        return entities.stream()
                .map(this::ofEntityToDto)
                .collect(Collectors.toList());
    }

    public UsuarioEntity ofModelToEntity(Usuario model) {
        if (model == null) return null;
        return new UsuarioEntity(
                model.getUsername(),
                model.getPassword(),
                model.getEmail(),
                model.getRol(),
                model.isActivo()
        );
    }
}
