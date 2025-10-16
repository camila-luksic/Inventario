package com.example.demo.application.dto;

import java.time.LocalDateTime;

public class MovimientoInventarioDto {
    private Long id;
    private Long productoId;
    private Long origenId;
    private Long destinoId;
    private int cantidad;
    private String tipo;
    private LocalDateTime fechaMovimiento;
    private String motivo;

    public MovimientoInventarioDto(Long id, Long productoId, Long origenId, Long destinoId, int cantidad, String tipo, LocalDateTime fechaMovimiento, String motivo) {
        this.id = id;
        this.productoId = productoId;
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fechaMovimiento = fechaMovimiento;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public Long getProductoId() { return productoId; }
    public Long getOrigenId() { return origenId; }
    public Long getDestinoId() { return destinoId; }
    public int getCantidad() { return cantidad; }
    public String getTipo() { return tipo; }
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public String getMotivo() { return motivo; }
}
