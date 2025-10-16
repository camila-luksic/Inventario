package com.example.demo.application.port.out;

import com.example.demo.application.dto.StockSucursalDto;
import com.example.demo.domain.models.StockSucursal;

import java.util.List;

public interface StockSucursalRepositoryPort {
    List<StockSucursalDto> getAll();
    StockSucursalDto getById(long id);
    StockSucursalDto save(StockSucursal stock);
}
