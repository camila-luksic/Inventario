package com.example.demo.application.services;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.application.dto.ProductoDto;
import com.example.demo.application.port.out.MarcaRepositoryPort;
import com.example.demo.application.port.out.ProductoRepositoryPort;
import com.example.demo.domain.models.Marca;
import com.example.demo.domain.models.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoServices {
    private final ProductoRepositoryPort productoRepository;
    private final MarcaRepositoryPort marcaRepository;

    public ProductoServices(ProductoRepositoryPort productoRepository, MarcaRepositoryPort marcaRepository){
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<ProductoDto> getAll(){ return productoRepository.getAll(); }
    public ProductoDto getById(long id){ return productoRepository.getById(id); }
    public ProductoDto save(Producto p){ return productoRepository.save(p); }

    public ProductoDto save(ProductoDto dto){
       if(dto.getMarcaId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "marcaId is required");
        }

        MarcaDto mDto = marcaRepository.getById(dto.getMarcaId());
        if(mDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca not found with id=" + dto.getMarcaId());
        }

        Marca marca = new Marca(mDto.getId(), mDto.getNombre(), mDto.getFoto());

        Producto p = new Producto(
                dto.getId() != null ? dto.getId() : 0,
                dto.getNombre(),
                marca,
                dto.getDescripcion(),
                dto.isActivo(),
                dto.getFoto()
        );
        return productoRepository.save(p);
    }

    public ProductoDto update(long id, ProductoDto dto){
        if(dto.getMarcaId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "marcaId is required");
        MarcaDto mDto = marcaRepository.getById(dto.getMarcaId());
        if(mDto == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca not found with id=" + dto.getMarcaId());

        Marca marca = new Marca(mDto.getId(), mDto.getNombre(), mDto.getFoto());
        Producto p = new Producto(id, dto.getNombre(), marca, dto.getDescripcion(), dto.isActivo(), dto.getFoto());
        return productoRepository.save(p);
    }

    public void delete(long id){
        productoRepository.deleteById(id);
    }
}
