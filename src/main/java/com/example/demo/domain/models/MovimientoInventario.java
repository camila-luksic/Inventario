package com.example.demo.domain.models;


import java.time.LocalDateTime;

public class MovimientoInventario {

    private long id;
    private Lote lote;
    private Producto producto;
    private Sucursal origen;
    private Sucursal destino;
    private int cantidad;
    private LocalDateTime fechaMovimiento;
    private String tipoMovimiento; // "INGRESO", "SALIDA", "TRANSFERENCIA"
    private String motivo; // ej: "Vencimiento", "Venta", "Ajuste", "Traslado"

    public MovimientoInventario() {
    }

    public MovimientoInventario(long id,Lote lote , Producto producto, Sucursal origen, Sucursal destino, int cantidad, LocalDateTime fechaMovimiento, String tipoMovimiento, String motivo) {
        this.id = id;
        this.producto = producto;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.lote = lote;
        this.motivo = motivo;
    }

    public long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Sucursal getOrigen() {
        return origen;
    }

    public Sucursal getDestino() {
        return destino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public Lote getLote() {
        return lote;
    }
    public String getMotivo() {
        return motivo;
    }
}

