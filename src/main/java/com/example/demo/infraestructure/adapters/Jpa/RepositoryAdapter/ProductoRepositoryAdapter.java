package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.ProductoDto;
import com.example.demo.domain.models.Producto;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.ProductoJpaRepository;
import com.example.demo.application.port.out.ProductoRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.ProductoMapper;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.MarcaJpaRepository;
import com.example.demo.infraestructure.adapters.entity.MarcaEntity;
import com.example.demo.infraestructure.adapters.entity.ProductoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {
    private final ProductoJpaRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final MarcaJpaRepository marcaJpaRepository;

    public ProductoRepositoryAdapter(ProductoJpaRepository productoRepository, ProductoMapper productoMapper, MarcaJpaRepository marcaJpaRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.marcaJpaRepository = marcaJpaRepository;
    }

    @Override
    public List<ProductoDto> getAll(){
        return productoMapper.offEntitytoDto(productoRepository.findAll());
    }

    @Override
    public ProductoDto getById(long id){
        return productoMapper.ofEntitytoDto(productoRepository.findById(id).orElse(null));
    }

    @Override
    public ProductoDto save(Producto p){
        MarcaEntity marcaEntity = null;
        if(p.getMarca() != null){
            Long marcaId = p.getMarca().getId();
            if(marcaId != 0){
                marcaEntity = marcaJpaRepository.findById(marcaId).orElse(null);
            }
        }
        if(p.getId() > 0){
            ProductoEntity existing = productoRepository.findById(p.getId()).orElse(null);
            if(existing != null){
                existing.setNombre(p.getNombre());
                if(marcaEntity != null) existing.setMarca(marcaEntity);
                existing.setDescripcion(p.getDescripcion());
                existing.setActivo(p.isActivo());
                existing.setFoto(p.getFoto());
                ProductoEntity saved = productoRepository.save(existing);
                return productoMapper.ofEntitytoDto(saved);
            }
        }

        ProductoEntity entityToSave = productoMapper.ofModeltoEntity(p, marcaEntity);
        ProductoEntity e = productoRepository.save(entityToSave);
        return productoMapper.ofEntitytoDto(e);
    }

    @Override
    public void deleteById(long id){
        productoRepository.deleteById(id);
    }
}
