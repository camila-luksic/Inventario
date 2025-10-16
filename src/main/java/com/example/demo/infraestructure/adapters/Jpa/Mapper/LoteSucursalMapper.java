package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LoteSucursalMapper {
    public LoteSucursalDto ofEntitytoDto(LoteSucursalEntity e){
        return new LoteSucursalDto(e.getId(), e.getLote() != null ? e.getLote().getId() : null, e.getSucursal() != null ? e.getSucursal().getId() : null, e.getCantidad());
    }

    public List<LoteSucursalDto> offEntitytoDto(List<LoteSucursalEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
