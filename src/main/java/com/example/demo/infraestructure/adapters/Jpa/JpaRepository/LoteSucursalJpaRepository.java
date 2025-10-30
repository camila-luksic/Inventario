package com.example.demo.infraestructure.adapters.Jpa.JpaRepository;

import com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteSucursalJpaRepository extends JpaRepository<LoteSucursalEntity, Long> {
    Optional<LoteSucursalEntity> findByLoteIdAndSucursalId(Long loteId, Long sucursalId);
}
