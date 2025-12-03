package com.example.demo.infraestructure.adapters.Jpa.RepositoryAdapter;

import com.example.demo.application.dto.MovimientoInventarioDto;
import com.example.demo.domain.models.Producto;
import com.example.demo.infraestructure.adapters.Jpa.JpaRepository.*;
import com.example.demo.infraestructure.adapters.Jpa.Mapper.MovimientoInventarioMapper;
import com.example.demo.infraestructure.adapters.entity.MovimientoInventarioEntity;
import com.example.demo.application.port.out.MovimientoInventarioRepositoryPort;
import com.example.demo.domain.models.MovimientoInventario;
import com.example.demo.infraestructure.adapters.entity.LoteEntity;
import com.example.demo.infraestructure.adapters.entity.ProductoEntity;
import com.example.demo.infraestructure.adapters.entity.SucursalEntity;
import com.example.demo.infraestructure.adapters.entity.TipoMovimiento;
import com.example.demo.infraestructure.adapters.entity.StockSucursalEntity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioRepositoryAdapter implements MovimientoInventarioRepositoryPort {
    
    private final MovimientoInventarioJpaRepository repo;
    private final MovimientoInventarioMapper mapper;
    private final StockSucursalJpaRepository stockSucursalRepo;
    private final ProductoJpaRepository productoRepo;
    private final SucursalJpaRepository sucursalRepo;
    private final LoteJpaRepository loteRepo;

    @Autowired
    public MovimientoInventarioRepositoryAdapter(
            MovimientoInventarioJpaRepository repo,
            MovimientoInventarioMapper mapper,
            StockSucursalJpaRepository stockSucursalRepo,
            ProductoJpaRepository productoRepo,
            SucursalJpaRepository sucursalRepo,
            LoteJpaRepository loteRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.stockSucursalRepo = stockSucursalRepo;
        this.productoRepo = productoRepo;
        this.sucursalRepo = sucursalRepo;
        this.loteRepo = loteRepo;
    }

    @Override
    public List<MovimientoInventarioDto> getAll(){
        return mapper.offEntitytoDto(repo.findAll());
    }

    @Override
    public MovimientoInventarioDto getById(long id){
        return mapper.ofEntitytoDto(repo.findById(id).orElse(null));
    }

    @Override
    public MovimientoInventarioDto save(
            MovimientoInventario movimientoInventario
    ){
        // Determine movement type
        String tipo = movimientoInventario.getTipoMovimiento();
        int cantidad = movimientoInventario.getCantidad();
        ProductoEntity productoEntity = movimientoInventario.getProducto() != null ? productoRepo.findById(movimientoInventario.getProducto().getId()).orElse(null) : null;
        SucursalEntity origenEntity = movimientoInventario.getOrigen() != null ? sucursalRepo.findById(movimientoInventario.getOrigen().getId()).orElse(null) : null;
        SucursalEntity destinoEntity = movimientoInventario.getDestino() != null ? sucursalRepo.findById(movimientoInventario.getDestino().getId()).orElse(null) : null;

        // Handle stock movement
        if ("TRANSFERENCIA".equals(tipo)) {
            // Decrease stock in origin
            if (origenEntity != null && productoEntity != null) {
                Optional<StockSucursalEntity> stockOrigenOpt = stockSucursalRepo.findByProductoIdAndSucursalId(productoEntity.getId(), origenEntity.getId());
                if (stockOrigenOpt.isPresent()) {
                    StockSucursalEntity stockOrigen = stockOrigenOpt.get();
                    if (stockOrigen.getCantidad() < cantidad) {
                        throw new IllegalArgumentException("No hay suficiente stock en el origen para transferir");
                    }
                    stockOrigen.setCantidad(stockOrigen.getCantidad() - cantidad);
                    stockSucursalRepo.save(stockOrigen);
                } else {
                    throw new IllegalArgumentException("No existe stock en el origen para transferir");
                }
            }
            // Increase stock in destination
            if (destinoEntity != null && productoEntity != null) {
                Optional<StockSucursalEntity> stockDestinoOpt = stockSucursalRepo.findByProductoIdAndSucursalId(productoEntity.getId(), destinoEntity.getId());
                StockSucursalEntity stockDestino;
                if (stockDestinoOpt.isPresent()) {
                    stockDestino = stockDestinoOpt.get();
                    stockDestino.setCantidad(stockDestino.getCantidad() + cantidad);
                } else {
                    stockDestino = new StockSucursalEntity(destinoEntity, productoEntity, cantidad);
                }
                stockSucursalRepo.save(stockDestino);
            }
        } else if ("SALIDA".equals(tipo)) {
            // Decrease stock in origin
            if (origenEntity != null && productoEntity != null) {
                Optional<StockSucursalEntity> stockOrigenOpt = stockSucursalRepo.findByProductoIdAndSucursalId(productoEntity.getId(), origenEntity.getId());
                if (stockOrigenOpt.isPresent()) {
                    StockSucursalEntity stockOrigen = stockOrigenOpt.get();
                    if (stockOrigen.getCantidad() < cantidad) {
                        throw new IllegalArgumentException("No hay suficiente stock en el origen para salida");
                    }
                    stockOrigen.setCantidad(stockOrigen.getCantidad() - cantidad);
                    stockSucursalRepo.save(stockOrigen);
                } else {
                    throw new IllegalArgumentException("No existe stock en el origen para salida");
                }
            }
        } else if ("INGRESO".equals(tipo)) {
            // Increase stock in destination
            if (destinoEntity != null && productoEntity != null) {
                Optional<StockSucursalEntity> stockDestinoOpt = stockSucursalRepo.findByProductoIdAndSucursalId(productoEntity.getId(), destinoEntity.getId());
                StockSucursalEntity stockDestino;
                if (stockDestinoOpt.isPresent()) {
                    stockDestino = stockDestinoOpt.get();
                    stockDestino.setCantidad(stockDestino.getCantidad() + cantidad);
                } else {
                    stockDestino = new StockSucursalEntity(destinoEntity, productoEntity, cantidad);
                }
                stockSucursalRepo.save(stockDestino);
            }
        }

        MovimientoInventarioEntity e = new MovimientoInventarioEntity(
                movimientoInventario.getLote() != null ? loteRepo.findById(movimientoInventario.getLote().getId()).orElse(null) : null,
                productoEntity,
                origenEntity,
                destinoEntity,
                cantidad,
                tipo != null ? TipoMovimiento.valueOf(tipo) : null,
                movimientoInventario.getFechaMovimiento(),
                movimientoInventario.getMotivo()
        );
        MovimientoInventarioEntity saved = repo.save(e);
        return mapper.ofEntitytoDto(saved);
    }

    public MovimientoInventarioDto saveFromEntities(
            LoteEntity lote,
            ProductoEntity producto,
            SucursalEntity origen,
            SucursalEntity destino,
            int cantidad,
            com.example.demo.infraestructure.adapters.entity.TipoMovimiento tipo,
            java.time.LocalDateTime fechaMovimiento,
            String motivo,
            int cantidadReal
    ) {
        // Actualizar stockSucursal
        Optional<StockSucursalEntity> optionalStock = stockSucursalRepo.findByProductoIdAndSucursalId(producto.getId(), destino.getId());
        StockSucursalEntity stockEntity;
        if (optionalStock.isPresent()) {
            stockEntity = optionalStock.get();
            if (tipo == com.example.demo.infraestructure.adapters.entity.TipoMovimiento.INGRESO) {
                stockEntity.setCantidad(stockEntity.getCantidad() + cantidad);
            } else if (tipo == com.example.demo.infraestructure.adapters.entity.TipoMovimiento.SALIDA) {
                if (stockEntity.getCantidad() < cantidad) {
                    throw new IllegalArgumentException("No hay suficiente stock para realizar la salida");
                }
                stockEntity.setCantidad(stockEntity.getCantidad() - cantidad);
            }
        } else {
            if (tipo == com.example.demo.infraestructure.adapters.entity.TipoMovimiento.INGRESO) {
                stockEntity = new StockSucursalEntity(destino, producto, cantidad);
            } else {
                throw new IllegalArgumentException("No existe stock para salida en esta sucursal");
            }
        }
        stockSucursalRepo.save(stockEntity);
        MovimientoInventarioEntity entity = new MovimientoInventarioEntity(
                lote,
                producto,
                origen,
                destino,
                cantidadReal,
                tipo,
                fechaMovimiento,
                motivo
        );
        entity = repo.save(entity);
        return mapper.ofEntitytoDto(entity);
    }
}
