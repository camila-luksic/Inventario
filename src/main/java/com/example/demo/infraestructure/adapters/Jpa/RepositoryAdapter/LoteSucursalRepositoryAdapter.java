package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.LoteSucursalJpaRepository;
import com.example.demo.application.port.out.LoteSucursalRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.LoteSucursalMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoteSucursalRepositoryAdapter implements LoteSucursalRepositoryPort {
    private final LoteSucursalJpaRepository repo;
    private final LoteSucursalMapper mapper;

    public LoteSucursalRepositoryAdapter(LoteSucursalJpaRepository repo, LoteSucursalMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<LoteSucursalDto> getAll(){
        return mapper.offEntitytoDto(repo.findAll());
    }

    @Override
    public LoteSucursalDto getById(long id){
        return mapper.ofEntitytoDto(repo.findById(id).orElse(null));
    }

    @Override
    public LoteSucursalDto save(com.example.demo.domain.models.LoteSucursal loteSucursal){
        com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity e = new com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity(null, null, loteSucursal.getCantidad());
        e = repo.save(e);
        return mapper.ofEntitytoDto(e);
    }
}
