package com.example.demo.infraestructure.adapters.Jpa.JpaRepository;

import com.example.demo.infraestructure.adapters.entity.LoteSucursalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteSucursalJpaRepository extends JpaRepository<LoteSucursalEntity, Long> {
}
