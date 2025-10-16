package com.example.demo.application.port.out;

import com.example.demo.application.dto.LoteDto;
import com.example.demo.domain.models.Lote;

import java.util.List;
import java.util.Optional;

public interface LoteRepositoryPort {
    List<Lote> getAll();
    Optional<Lote> getById(Long id);
    Lote save(Lote lote);
    Lote save(Lote lote, Long productoId);
}
