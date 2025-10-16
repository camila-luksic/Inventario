package com.example.demo.domain.models;

import java.time.LocalDate;

public class Lote {

    private long id;
    private Producto producto;
    private String codigoLote;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private boolean notificacionActiva;
    private boolean dadoDeBaja;

    public Lote() {
    }

    public Lote(long id, Producto producto, String codigoLote, LocalDate fechaVencimiento, int cantidad, boolean notificacionActiva) {
        this.id = id;
        this.producto = producto;
        this.codigoLote = codigoLote;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.notificacionActiva = notificacionActiva;
        this.dadoDeBaja = false;
    }

    public long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isNotificacionActiva() {
        return notificacionActiva;
    }

    public boolean isDadoDeBaja() {
        return dadoDeBaja;
    }
}
