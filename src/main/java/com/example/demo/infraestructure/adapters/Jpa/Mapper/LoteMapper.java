package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.LoteDto;
import com.example.demo.domain.models.Lote;
import com.example.demo.domain.models.Producto;
import com.example.demo.infraestructure.adapters.entity.LoteEntity;
import com.example.demo.infraestructure.adapters.entity.ProductoEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LoteMapper {
    
    public LoteDto ofModelToDto(Lote lote) {
        return new LoteDto(
            lote.getId(),
            lote.getProducto().getId(),
            lote.getCodigoLote(),
            lote.getFechaVencimiento(),
            lote.getCantidad()
        );
    }
    
    public Lote ofDtoToModel(LoteDto dto) {
        return new Lote(
            dto.getId() != null ? dto.getId() : 0,
            null,
            dto.getCodigoLote(),
            dto.getFechaVencimiento(),
            dto.getCantidad(),
            true
        );
    }

    public LoteEntity ofModelToEntity(Lote lote) {
       return ofModelToEntity(lote, null);
    }

    public LoteEntity ofModelToEntity(Lote lote, ProductoEntity productoEntity) {
        ProductoEntity p = productoEntity;
       return new LoteEntity(
            p,
            lote.getCodigoLote(),
            lote.getFechaVencimiento(),
            lote.getCantidad()
        );
    }
    
    public Lote ofEntityToModel(LoteEntity entity) {
        Producto producto = null;
        if(entity.getProducto() != null) {
            producto = new Producto(
                entity.getProducto().getId(),
                entity.getProducto().getNombre(),
                null,
                entity.getProducto().getDescripcion(),
                entity.getProducto().isActivo()
            );
        }
        return new Lote(
            entity.getId(),
            producto,
            entity.getCodigoLote(),
            entity.getFechaVencimiento(),
            entity.getCantidad(),
            entity.isNotificacionActiva()
        );
    }
}
