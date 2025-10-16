package com.example.demo.application.services;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.application.port.out.MovimientoInventarioRepositoryPort;
import com.example.demo.domain.models.MovimientoInventario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInventarioServices {
    private MovimientoInventarioRepositoryPort movimientoRepository;
    public MovimientoInventarioServices(MovimientoInventarioRepositoryPort movimientoRepository){ this.movimientoRepository = movimientoRepository; }

    public List<MovimientoInventarioDto> getAll(){ return movimientoRepository.getAll(); }
    public MovimientoInventarioDto getById(long id){ return movimientoRepository.getById(id); }
    public MovimientoInventarioDto save(MovimientoInventario m){ return movimientoRepository.save(m); }
}
