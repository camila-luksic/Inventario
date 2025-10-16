package com.example.demo.application.port.out;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.domain.models.MovimientoInventario;

import java.util.List;

public interface MovimientoInventarioRepositoryPort {
    List<MovimientoInventarioDto> getAll();
    MovimientoInventarioDto getById(long id);
    MovimientoInventarioDto save(MovimientoInventario movimiento);
}
