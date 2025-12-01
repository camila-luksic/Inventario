package com.example.demo.infraestructure.adapters.Jpa.JpaRepository;

import com.example.demo.infraestructure.adapters.entity.StockSucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockSucursalJpaRepository extends JpaRepository<StockSucursalEntity, Long> {
    Optional<StockSucursalEntity> findByProductoIdAndSucursalId(Long productoId, Long sucursalId);
}
