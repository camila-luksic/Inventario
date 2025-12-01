package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.SucursalDto;
import com.example.demo.domain.models.Sucursal;
import com.example.demo.infraestructure.adapters.entity.SucursalEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {
    public SucursalEntity ofModeltoEntity(Sucursal s){
        return new SucursalEntity(s.getNombre(), s.getDireccion(), s.getHorarioApertura(), s.getHorarioCierre());
    }

    public SucursalDto ofEntitytoDto(SucursalEntity e){
        return new SucursalDto(e.getId(), e.getNombre(), e.getDireccion(), e.getHorarioApertura(), e.getHorarioCierre());
    }

    public List<SucursalDto> offEntitytoDto(List<SucursalEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
