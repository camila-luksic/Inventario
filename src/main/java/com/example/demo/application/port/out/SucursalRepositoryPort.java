package com.example.demo.application.port.out;

import com.example.demo.application.dto.SucursalDto;
import com.example.demo.domain.models.Sucursal;

import java.util.List;

public interface SucursalRepositoryPort {
    List<SucursalDto> getAll();
    SucursalDto getById(long id);
    SucursalDto save(Sucursal sucursal);
    void deleteById(long id);
}
