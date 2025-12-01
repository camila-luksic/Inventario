package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.ProductoDto;
import com.example.demo.domain.models.Producto;
import com.example.demo.infraestructure.adapters.entity.MarcaEntity;
import com.example.demo.infraestructure.adapters.entity.ProductoEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoEntity ofModeltoEntity(Producto p, MarcaEntity marcaEntity){
        ProductoEntity e = new ProductoEntity(p.getNombre(), marcaEntity, p.getDescripcion(), p.isActivo(), p.getFoto());
        return e;
    }

    public ProductoDto ofEntitytoDto(ProductoEntity e){
        return new ProductoDto(e.getId(), e.getNombre(), e.getMarca() != null ? e.getMarca().getId() : null, e.getDescripcion(), e.isActivo(), e.getFoto());
    }

    public List<ProductoDto> offEntitytoDto(List<ProductoEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
