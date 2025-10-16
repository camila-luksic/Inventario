package com.example.demo.application.port.out;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.domain.models.LoteSucursal;

import java.util.List;

public interface LoteSucursalRepositoryPort {
    List<LoteSucursalDto> getAll();
    LoteSucursalDto getById(long id);
    LoteSucursalDto save(LoteSucursal loteSucursal);
}
