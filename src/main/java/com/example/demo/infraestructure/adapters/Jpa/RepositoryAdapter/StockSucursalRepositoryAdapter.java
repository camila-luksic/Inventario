package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.StockSucursalDto;
import com.example.demo.domain.models.StockSucursal;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.StockSucursalJpaRepository;
import com.example.demo.application.port.out.StockSucursalRepositoryPort;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.StockSucursalMapper;
import com.example.demo.infraestructure.adapters.entity.StockSucursalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSucursalRepositoryAdapter implements StockSucursalRepositoryPort {
    
    private final StockSucursalJpaRepository stockSucursalRepository;
    private final StockSucursalMapper stockSucursalMapper;

    @Autowired
    public StockSucursalRepositoryAdapter(StockSucursalJpaRepository stockSucursalRepository, 
                                           StockSucursalMapper stockSucursalMapper) {
        this.stockSucursalRepository = stockSucursalRepository;
        this.stockSucursalMapper = stockSucursalMapper;
    }

    @Override
    public List<StockSucursalDto> getAll(){
        return stockSucursalMapper.offEntitytoDto(stockSucursalRepository.findAll());
    }

    @Override
    public StockSucursalDto getById(long id){
        return stockSucursalMapper.ofEntitytoDto(stockSucursalRepository.findById(id).orElse(null));
    }

    @Override
    public StockSucursalDto save(StockSucursal s){
        StockSucursalEntity e = stockSucursalRepository.save(stockSucursalMapper.ofModeltoEntity(s));
        return stockSucursalMapper.ofEntitytoDto(e);
    }
}
