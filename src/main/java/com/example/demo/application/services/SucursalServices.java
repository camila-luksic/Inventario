package com.example.demo.application.services;

import com.example.demo.application.dto.SucursalDto;
import com.example.demo.application.port.out.SucursalRepositoryPort;
import com.example.demo.domain.models.Sucursal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SucursalServices {
    private final SucursalRepositoryPort sucursalRepository;

    public SucursalServices(SucursalRepositoryPort sucursalRepository){ this.sucursalRepository = sucursalRepository; }

    public List<SucursalDto> getAll(){ return sucursalRepository.getAll(); }
    public SucursalDto getById(long id){ return sucursalRepository.getById(id); }
    public SucursalDto save(Sucursal s){ return sucursalRepository.save(s); }
    public SucursalDto update(long id, Sucursal s){
        SucursalDto existing = sucursalRepository.getById(id);
        if(existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal not found");
        Sucursal toSave = new Sucursal(id, s.getNombre(), s.getDireccion(), s.getHorarioApertura(), s.getHorarioCierre());
        return sucursalRepository.save(toSave);
    }

    public void delete(long id){
        SucursalDto existing = sucursalRepository.getById(id);
        if(existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal not found");
        sucursalRepository.deleteById(id);
    }
}
