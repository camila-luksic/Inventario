package com.example.demo.application.port.out;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.domain.models.Marca;

import java.util.List;

public interface MarcaRepositoryPort {
    List<MarcaDto> getAll();
    MarcaDto getById(long id);
    MarcaDto save(Marca marca);
    void deleteById(long id);
}
