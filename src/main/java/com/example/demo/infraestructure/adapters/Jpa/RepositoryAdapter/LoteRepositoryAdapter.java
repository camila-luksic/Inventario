package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.domain.models.Lote;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.LoteJpaRepository;
import com.example.demo.application.port.out.LoteRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.LoteMapper;
import com.example.demo.infraestructure.adapters.entity.LoteEntity;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.ProductoJpaRepository;
import com.example.demo.infraestructure.adapters.entity.ProductoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoteRepositoryAdapter implements LoteRepositoryPort {

    private final LoteJpaRepository loteRepository;
    private final LoteMapper loteMapper;
    private final ProductoJpaRepository productoJpaRepository;

    public LoteRepositoryAdapter(LoteJpaRepository loteRepository, LoteMapper loteMapper, ProductoJpaRepository productoJpaRepository) {
        this.loteRepository = loteRepository;
        this.loteMapper = loteMapper;
        this.productoJpaRepository = productoJpaRepository;
    }

    @Override
    public List<Lote> getAll() {
        return loteRepository.findAll()
                .stream()
                .map(loteMapper::ofEntityToModel)
                .toList();
    }

    @Override
    public Optional<Lote> getById(Long id) {
        return loteRepository.findById(id)
                .map(loteMapper::ofEntityToModel);
    }

    @Override
    public Lote save(Lote lote) {
        ProductoEntity productoEntity = null;
        if(lote.getProducto() != null){
            long pid = lote.getProducto().getId();
            if(pid > 0) productoEntity = productoJpaRepository.findById(pid).orElse(null);
        }
        if(lote.getId() > 0) {
            Optional<LoteEntity> maybe = loteRepository.findById(lote.getId());
            if(maybe.isPresent()) {
                LoteEntity existing = maybe.get();
                if(productoEntity != null) existing.setProducto(productoEntity);
                existing.setCodigoLote(lote.getCodigoLote());
                existing.setFechaVencimiento(lote.getFechaVencimiento());
                existing.setCantidad(lote.getCantidad());
                existing.setNotificacionActiva(lote.isNotificacionActiva());
                LoteEntity saved = loteRepository.save(existing);
                return loteMapper.ofEntityToModel(saved);
            }
        }
        LoteEntity entity = loteMapper.ofModelToEntity(lote, productoEntity);
        LoteEntity saved = loteRepository.save(entity);
        return loteMapper.ofEntityToModel(saved);
    }

    @Override
    public Lote save(Lote lote, Long productoId) {
        ProductoEntity productoEntity = null;
        if(productoId != null && productoId > 0) {
            productoEntity = productoJpaRepository.findById(productoId).orElse(null);
            if(productoEntity == null) {
                throw new RuntimeException("Producto not found for id: " + productoId);
            }
        }
       if(lote.getId() > 0) {
            Optional<LoteEntity> maybe = loteRepository.findById(lote.getId());
            if(maybe.isPresent()) {
                LoteEntity existing = maybe.get();
                if(productoEntity != null) existing.setProducto(productoEntity);
                existing.setCodigoLote(lote.getCodigoLote());
                existing.setFechaVencimiento(lote.getFechaVencimiento());
                existing.setCantidad(lote.getCantidad());
                existing.setNotificacionActiva(lote.isNotificacionActiva());
                LoteEntity saved = loteRepository.save(existing);
                return loteMapper.ofEntityToModel(saved);
            }
        }
        LoteEntity entity = loteMapper.ofModelToEntity(lote, productoEntity);
        LoteEntity saved = loteRepository.save(entity);

        return loteMapper.ofEntityToModel(saved);
    }

    @Override
    public Lote darDeBaja(Long id) {
        Optional<LoteEntity> maybe = loteRepository.findById(id);
        if(maybe.isPresent()) {
            LoteEntity existing = maybe.get();
            existing.setDadoDeBaja(true);
            LoteEntity saved = loteRepository.save(existing);
            return loteMapper.ofEntityToModel(saved);
        }
        throw new RuntimeException("Lote not found for id: " + id);
    }
}