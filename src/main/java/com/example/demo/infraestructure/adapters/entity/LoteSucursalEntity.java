package com.example.demo.infraestructure.adapters.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lote_sucursal",
       uniqueConstraints = @UniqueConstraint(name = "uk_lote_sucursal", columnNames = {"lote_id","sucursal_id"}),
       indexes = {
           @Index(name = "ix_ls_sucursal", columnList = "sucursal_id"),
           @Index(name = "ix_ls_lote", columnList = "lote_id")
       })
public class LoteSucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ls_lote"))
    private LoteEntity lote;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ls_sucursal"))
    private SucursalEntity sucursal;

    @Column(nullable = false)
    private int cantidad;

    protected LoteSucursalEntity() { }

    public LoteSucursalEntity(LoteEntity lote, SucursalEntity sucursal, int cantidad) {
        this.lote = lote;
        this.sucursal = sucursal;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public LoteEntity getLote() { return lote; }
    public SucursalEntity getSucursal() { return sucursal; }
    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

