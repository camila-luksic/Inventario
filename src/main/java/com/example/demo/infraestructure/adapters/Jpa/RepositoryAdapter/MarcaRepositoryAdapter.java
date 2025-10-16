package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.MarcaDto;
import com.example.demo.domain.models.Marca;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.MarcaJpaRepository;
import com.example.demo.application.port.out.MarcaRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.MarcaMapper;
import com.example.demo.infraestructure.adapters.entity.MarcaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaRepositoryAdapter implements MarcaRepositoryPort {
    private final MarcaJpaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaRepositoryAdapter(MarcaJpaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }

    @Override
    public List<MarcaDto> getAll(){
        List<MarcaEntity> list = marcaRepository.findAll();
        return marcaMapper.offEntitytoDto(list);
    }

    @Override
    public MarcaDto getById(long id){
        return marcaMapper.ofEntitytoDto(marcaRepository.findById(id).orElse(null));
    }

    @Override
    public MarcaDto save(Marca m){
        if(m.getNombre() != null){
            var other = marcaRepository.findAll().stream().filter(en -> en.getNombre().equalsIgnoreCase(m.getNombre())).findFirst().orElse(null);
            if(other != null && (m.getId() == 0 || other.getId() != m.getId())){
                throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.CONFLICT, "Marca name already exists");
            }
        }

        if(m.getId() > 0){
            MarcaEntity existing = marcaRepository.findById(m.getId()).orElse(null);
            if(existing != null){
                existing.setNombre(m.getNombre());
                MarcaEntity saved = marcaRepository.save(existing);
                return marcaMapper.ofEntitytoDto(saved);
            }
        }
        MarcaEntity e = marcaRepository.save(marcaMapper.ofModeltoEntity(m));
        return marcaMapper.ofEntitytoDto(e);
    }

    @Override
    public void deleteById(long id){
        marcaRepository.deleteById(id);
    }
}
