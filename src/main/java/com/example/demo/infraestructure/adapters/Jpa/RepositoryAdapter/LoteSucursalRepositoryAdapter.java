package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.LoteJpaRepository;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.LoteSucursalJpaRepository;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.SucursalJpaRepository;
import com.example.demo.application.port.out.LoteSucursalRepositoryPort;
import com.example.demo.domain.models.LoteSucursal;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.LoteSucursalMapper;
import com.example.demo.infraestructure.adapters.entity.LoteEntity;
import com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity;
import com.example.demo.infraestructure.adapters.entity.SucursalEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class LoteSucursalRepositoryAdapter implements LoteSucursalRepositoryPort {
    private final LoteSucursalJpaRepository repo;
    private final LoteJpaRepository loteRepo;
    private final SucursalJpaRepository sucursalRepo;
    private final LoteSucursalMapper mapper;

    public LoteSucursalRepositoryAdapter(LoteSucursalJpaRepository repo, LoteJpaRepository loteRepo, SucursalJpaRepository sucursalRepo, LoteSucursalMapper mapper) {
        this.repo = repo;
        this.loteRepo = loteRepo;
        this.sucursalRepo = sucursalRepo;
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
    public LoteSucursalDto save(LoteSucursal loteSucursal){
        if (loteSucursal == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "loteSucursal is null");

        if (loteSucursal.getLote() == null || loteSucursal.getSucursal() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "lote or sucursal must be provided");
        }

        Long loteId = loteSucursal.getLote().getId();
        Long sucursalId = loteSucursal.getSucursal().getId();

        LoteEntity loteEntity = loteRepo.findById(loteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote not found: " + loteId));
        SucursalEntity sucursalEntity = sucursalRepo.findById(sucursalId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal not found: " + sucursalId));

        // if there's already a record for this lote+sucursal, increment cantidad
        return repo.findByLoteIdAndSucursalId(loteId, sucursalId)
                .map(existing -> {
                    existing.setCantidad(existing.getCantidad() + loteSucursal.getCantidad());
                    LoteSucursalEntity saved = repo.save(existing);
                    return mapper.ofEntitytoDto(saved);
                })
                .orElseGet(() -> {
                    LoteSucursalEntity e = new LoteSucursalEntity(loteEntity, sucursalEntity, loteSucursal.getCantidad());
                    LoteSucursalEntity saved = repo.save(e);
                    return mapper.ofEntitytoDto(saved);
                });
    }
}
