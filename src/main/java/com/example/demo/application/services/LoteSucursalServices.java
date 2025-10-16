package com.example.demo.application.services;

import com.example.demo.application.dto.LoteSucursalDto;
import com.example.demo.application.port.out.LoteSucursalRepositoryPort;
import com.example.demo.domain.models.LoteSucursal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoteSucursalServices {
    private final LoteSucursalRepositoryPort repoPort;

    public LoteSucursalServices(LoteSucursalRepositoryPort repoPort) {
        this.repoPort = repoPort;
    }

    public List<LoteSucursalDto> getAll(){ return repoPort.getAll(); }

    public LoteSucursalDto getById(long id){ return repoPort.getById(id); }

    public LoteSucursalDto save(LoteSucursal loteSucursal){ return repoPort.save(loteSucursal); }
}
