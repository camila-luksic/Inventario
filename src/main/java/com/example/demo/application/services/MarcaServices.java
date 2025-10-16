package com.example.demo.application.services;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.application.port.out.MarcaRepositoryPort;
import com.example.demo.domain.models.Marca;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaServices {
    private MarcaRepositoryPort marcaRepository;
    public MarcaServices(MarcaRepositoryPort marcaRepository){ this.marcaRepository = marcaRepository; }

    public List<MarcaDto> getAll(){ return marcaRepository.getAll(); }
    public MarcaDto getById(long id){ return marcaRepository.getById(id); }
    public MarcaDto save(Marca m){ return marcaRepository.save(m); }
    public MarcaDto update(long id, Marca m){
        MarcaDto existing = marcaRepository.getById(id);
        if(existing == null) throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Marca not found");
        Marca marcaToSave = new Marca(id, m.getNombre());
        return marcaRepository.save(marcaToSave);
    }

    public void delete(long id){
        MarcaDto existing = marcaRepository.getById(id);
        if(existing == null) throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Marca not found");
        try{
            marcaRepository.deleteById(id);
        }catch(Exception ex){
            throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting Marca: " + ex.getMessage());
        }
    }
}
