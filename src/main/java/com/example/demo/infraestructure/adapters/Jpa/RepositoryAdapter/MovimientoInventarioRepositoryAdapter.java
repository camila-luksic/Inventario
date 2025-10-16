package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.MovimientoInventarioMapper;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.MovimientoInventarioJpaRepository;
import com.example.demo.application.port.out.MovimientoInventarioRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovimientoInventarioRepositoryAdapter implements MovimientoInventarioRepositoryPort {
    private MovimientoInventarioJpaRepository repo;
    private MovimientoInventarioMapper mapper;

    @Override
    public List<MovimientoInventarioDto> getAll(){
        return mapper.offEntitytoDto(repo.findAll());
    }

    @Override
    public MovimientoInventarioDto getById(long id){
        return mapper.ofEntitytoDto(repo.findById(id).orElse(null));
    }

    @Override
    public MovimientoInventarioDto save(com.example.demo.domain.models.MovimientoInventario movimiento){
        com.example.demo.infraestructure.adapters.entity.MovimientoInventarioEntity e = new com.example.demo.infraestructure.adapters.entity.MovimientoInventarioEntity(
                null,
                null,
                null,
                null,
                movimiento.getCantidad(),
                null,
                movimiento.getFechaMovimiento(),
                null
        );
        e = repo.save(e);
        return mapper.ofEntitytoDto(e);
    }
}
