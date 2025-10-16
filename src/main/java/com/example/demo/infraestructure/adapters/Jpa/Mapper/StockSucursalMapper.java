package com.example.demo.infraestructure.adapters.Jpa.Mapper;

import com.example.demo.application.dto.StockSucursalDto;
import com.example.demo.domain.models.StockSucursal;
import com.example.demo.infraestructure.adapters.entity.StockSucursalEntity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StockSucursalMapper {
    public StockSucursalEntity ofModeltoEntity(StockSucursal s){
        return new StockSucursalEntity(null, null, s.getCantidad());
    }

    public StockSucursalDto ofEntitytoDto(StockSucursalEntity e){
        return new StockSucursalDto(e.getId(), e.getSucursal() != null ? e.getSucursal().getId() : null, e.getProducto() != null ? e.getProducto().getId() : null, e.getCantidad());
    }

    public List<StockSucursalDto> offEntitytoDto(List<StockSucursalEntity> list){
        return list.stream().map(this::ofEntitytoDto).toList();
    }
}
