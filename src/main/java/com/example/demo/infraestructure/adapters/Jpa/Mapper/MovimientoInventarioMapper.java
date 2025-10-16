package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.infraestructure.adapters.entity.MovimientoInventarioEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MovimientoInventarioMapper {
    public MovimientoInventarioDto ofEntitytoDto(MovimientoInventarioEntity e){
        return new MovimientoInventarioDto(e.getId(), e.getProducto() != null ? e.getProducto().getId() : null, e.getOrigen() != null ? e.getOrigen().getId() : null, e.getDestino() != null ? e.getDestino().getId() : null, e.getCantidad(), e.getTipo() != null ? e.getTipo().name() : null, e.getFechaMovimiento(), e.getMotivo());
    }

    public List<MovimientoInventarioDto> offEntitytoDto(List<MovimientoInventarioEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
