package com.example.demo.application.port.out;

import com.example.demo.application.dto.ProductoDto;
import com.example.demo.domain.models.Producto;

import java.util.List;

public interface ProductoRepositoryPort {
    List<ProductoDto> getAll();
    ProductoDto getById(long id);
    ProductoDto save(Producto producto);
    void deleteById(long id);
}
