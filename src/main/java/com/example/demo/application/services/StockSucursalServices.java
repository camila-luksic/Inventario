package com.example.demo.application.services;

import com.example.demo.application.dto.StockSucursalDto;
import com.example.demo.application.port.out.StockSucursalRepositoryPort;
import com.example.demo.domain.models.StockSucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSucursalServices {
    private StockSucursalRepositoryPort stockRepository;
    public StockSucursalServices(StockSucursalRepositoryPort stockRepository){ this.stockRepository = stockRepository; }

    public List<StockSucursalDto> getAll(){ return stockRepository.getAll(); }
    public StockSucursalDto getById(long id){ return stockRepository.getById(id); }
    public StockSucursalDto save(StockSucursal s){ return stockRepository.save(s); }
}
