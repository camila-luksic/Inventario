package com.example.demo.infraestructure.adapters.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes",
       uniqueConstraints = @UniqueConstraint(name = "uk_lote_codigo_producto", columnNames = {"codigo_lote","producto_id"}),
       indexes = {
           @Index(name = "ix_lote_producto", columnList = "producto_id"),
           @Index(name = "ix_lote_vencimiento", columnList = "fecha_vencimiento")
       })
public class LoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_lote_producto"))
    private ProductoEntity producto;

    @Column(name = "codigo_lote", nullable = false, length = 80)
    private String codigoLote;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "notificacion_activa", nullable = false)
    private boolean notificacionActiva = true;

    @Column(name = "dias_aviso", nullable = false)
    private int diasAviso = 30;

    @Column(name = "dado_de_baja", nullable = false)
    private boolean dadoDeBaja = false;

    protected LoteEntity() { }

    public LoteEntity(ProductoEntity producto, String codigoLote, LocalDate fechaVencimiento, int cantidad) {
        this.producto = producto;
        this.codigoLote = codigoLote;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public ProductoEntity getProducto() { return producto; }
    public String getCodigoLote() { return codigoLote; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public boolean isNotificacionActiva() { return notificacionActiva; }
    public int getDiasAviso() { return diasAviso; }
    public boolean isDadoDeBaja() { return dadoDeBaja; }

    public void setNotificacionActiva(boolean v) { this.notificacionActiva = v; }
    public void setDiasAviso(int dias) { this.diasAviso = dias; }
    public void setDadoDeBaja(boolean v) { this.dadoDeBaja = v; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }


    public void setProducto(ProductoEntity producto) { this.producto = producto; }
    public void setCodigoLote(String codigoLote) { this.codigoLote = codigoLote; }
    public void setFechaVencimiento(java.time.LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}
