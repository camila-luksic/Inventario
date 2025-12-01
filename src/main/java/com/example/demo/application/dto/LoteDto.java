package com.example.demo.application.dto;

import java.time.LocalDate;

public class LoteDto {
    private Long id;
    private Long productoId;
    private String codigoLote;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private boolean notificacionActiva;

    public LoteDto(Long id, Long productoId, String codigoLote, LocalDate fechaVencimiento, int cantidad, boolean notificacionActiva) {
        this.id = id;
        this.productoId = productoId;
        this.codigoLote = codigoLote;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.notificacionActiva = notificacionActiva;
    }

    public Long getId() { return id; }
    public Long getProductoId() { return productoId; }
    public String getCodigoLote() { return codigoLote; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public boolean isNotificacionActiva() { return notificacionActiva; }
}
