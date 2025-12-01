package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.domain.models.Marca;
import com.example.demo.infraestructure.adapters.entity.MarcaEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {
    public MarcaEntity ofModeltoEntity(Marca m){
        MarcaEntity e = new MarcaEntity(m.getNombre(), m.getFoto());
        return e;
    }

    public MarcaDto ofEntitytoDto(MarcaEntity e){
        return new MarcaDto(e.getId(), e.getNombre(), e.getFoto());
    }

    public List<MarcaDto> offEntitytoDto(List<MarcaEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
