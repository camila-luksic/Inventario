package com.example.demo.infraestructure.adapters.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_sucursal",
       uniqueConstraints = @UniqueConstraint(name = "uk_stock_prod_suc", columnNames = {"producto_id","sucursal_id"}),
       indexes = {
           @Index(name = "ix_stock_sucursal", columnList = "sucursal_id"),
           @Index(name = "ix_stock_producto", columnList = "producto_id")
       })
public class StockSucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_stock_sucursal"))
    private SucursalEntity sucursal;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_stock_producto"))
    private ProductoEntity producto;

    @Column(nullable = false)
    private int cantidad;

    protected StockSucursalEntity() { }

    public StockSucursalEntity(SucursalEntity sucursal, ProductoEntity producto, int cantidad) {
        this.sucursal = sucursal;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public SucursalEntity getSucursal() { return sucursal; }
    public ProductoEntity getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
