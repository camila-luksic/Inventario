package com.example.demo.infraestructure.adapters.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario",
       indexes = {
           @Index(name = "ix_mov_prod", columnList = "producto_id"),
           @Index(name = "ix_mov_origen", columnList = "origen_id"),
           @Index(name = "ix_mov_destino", columnList = "destino_id"),
           @Index(name = "ix_mov_fecha", columnList = "fecha_movimiento")
       })
public class MovimientoInventarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", foreignKey = @ForeignKey(name = "fk_mov_lote"))
    private LoteEntity lote;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_mov_producto"))
    private ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_id", foreignKey = @ForeignKey(name = "fk_mov_origen"))
    private SucursalEntity origen; // null si es INGRESO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", foreignKey = @ForeignKey(name = "fk_mov_destino"))
    private SucursalEntity destino; // null si es SALIDA

    @Column(nullable = false)
    private int cantidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMovimiento tipo;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    @Column(length = 300)
    private String motivo; // ej: "Vencimiento", "Venta", "Ajuste", "Traslado"

    protected MovimientoInventarioEntity() { }

    public MovimientoInventarioEntity(LoteEntity lote,
                                      ProductoEntity producto,
                                      SucursalEntity origen,
                                      SucursalEntity destino,
                                      int cantidad,
                                      TipoMovimiento tipo,
                                      LocalDateTime fechaMovimiento,
                                      String motivo) {
        this.lote = lote;
        this.producto = producto;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fechaMovimiento = fechaMovimiento;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public LoteEntity getLote() { return lote; }
    public ProductoEntity getProducto() { return producto; }
    public SucursalEntity getOrigen() { return origen; }
    public SucursalEntity getDestino() { return destino; }
    public int getCantidad() { return cantidad; }
    public TipoMovimiento getTipo() { return tipo; }
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public String getMotivo() { return motivo; }
}

