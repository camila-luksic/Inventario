package com.example.demo.application.services;

import com.example.demo.application.dto.LoteDto;
import com.example.demo.application.dto.ProductoDto;
import com.example.demo.application.port.out.LoteRepositoryPort;
import com.example.demo.application.port.out.ProductoRepositoryPort;
import com.example.demo.domain.models.Lote;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.LoteMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoteServices {

    private final LoteRepositoryPort loteRepositoryPort;
    private final LoteMapper dtoMapper; 
    private final ProductoRepositoryPort productoRepositoryPort;

    public LoteServices(LoteRepositoryPort loteRepositoryPort, LoteMapper dtoMapper, ProductoRepositoryPort productoRepositoryPort) {
        this.loteRepositoryPort = loteRepositoryPort;
        this.dtoMapper = dtoMapper;
        this.productoRepositoryPort = productoRepositoryPort;
    }
    
    public List<LoteDto> getAll() {
        return loteRepositoryPort.getAll()
                .stream()
                .map(dtoMapper::ofModelToDto)
                .toList();
    }
    
    public LoteDto getById(Long id) {
        return loteRepositoryPort.getById(id)
                .map(dtoMapper::ofModelToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote no encontrado"));
    }
    
    public LoteDto save(LoteDto dto) {
        if (dto.getProductoId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productoId is required");
        }

        ProductoDto prodDto = productoRepositoryPort.getById(dto.getProductoId());
        if (prodDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto not found");
        }

        Lote lote = dtoMapper.ofDtoToModel(dto);
        Lote saved = loteRepositoryPort.save(lote, dto.getProductoId());
        return dtoMapper.ofModelToDto(saved);
    }

    public LoteDto toggleNotification(Long loteId, boolean enabled){
        java.util.Optional<Lote> maybe = loteRepositoryPort.getById(loteId);
        Lote lote = maybe.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote not found"));
        try{
            java.lang.reflect.Field f = Lote.class.getDeclaredField("notificacionActiva");
            f.setAccessible(true);
            f.set(lote, enabled);
        }catch(Exception ex){ throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex); }
        Lote saved = loteRepositoryPort.save(lote);
        return dtoMapper.ofModelToDto(saved);
    }

    public LoteDto darDeBaja(Long loteId) {
        java.util.Optional<Lote> maybe = loteRepositoryPort.getById(loteId);
        Lote lote = maybe.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote not found"));
        try {
            java.lang.reflect.Field f = Lote.class.getDeclaredField("activo");
            f.setAccessible(true);
            f.set(lote, false);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        Lote saved = loteRepositoryPort.save(lote);
        return dtoMapper.ofModelToDto(saved);
    }
}
