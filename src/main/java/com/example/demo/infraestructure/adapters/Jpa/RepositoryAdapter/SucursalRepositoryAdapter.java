package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.SucursalDto;
import com.example.demo.domain.models.Sucursal;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.SucursalJpaRepository;
import com.example.demo.application.port.out.SucursalRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.SucursalMapper;
import com.example.demo.infraestructure.adapters.entity.SucursalEntity;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SucursalRepositoryAdapter implements SucursalRepositoryPort {
    private final SucursalJpaRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    public SucursalRepositoryAdapter(SucursalJpaRepository sucursalRepository, SucursalMapper sucursalMapper) {
        this.sucursalRepository = sucursalRepository;
        this.sucursalMapper = sucursalMapper;
    }

    @Override
    public List<SucursalDto> getAll(){
        return sucursalMapper.offEntitytoDto(sucursalRepository.findAll());
    }

    @Override
    public SucursalDto getById(long id){
        return sucursalMapper.ofEntitytoDto(sucursalRepository.findById(id).orElse(null));
    }

    @Override
    public SucursalDto save(Sucursal s){
        if(s.getNombre() != null){
            var other = sucursalRepository.findAll().stream().filter(en -> en.getNombre().equalsIgnoreCase(s.getNombre())).findFirst().orElse(null);
            if(other != null && (s.getId() == 0 || other.getId() != s.getId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Sucursal name already exists");
            }
        }

        if(s.getId() > 0){
            SucursalEntity existing = sucursalRepository.findById(s.getId()).orElse(null);
            if(existing != null){
                existing.setNombre(s.getNombre());
                existing.setDireccion(s.getDireccion());
                existing.setHorarioApertura(s.getHorarioApertura());
                existing.setHorarioCierre(s.getHorarioCierre());
                SucursalEntity saved = sucursalRepository.save(existing);
                return sucursalMapper.ofEntitytoDto(saved);
            }
        }

        SucursalEntity e = sucursalRepository.save(sucursalMapper.ofModeltoEntity(s));
        return sucursalMapper.ofEntitytoDto(e);
    }

    @Override
    public void deleteById(long id){
        sucursalRepository.deleteById(id);
    }
}
