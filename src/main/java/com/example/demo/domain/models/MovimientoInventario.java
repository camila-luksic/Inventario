package com.example.demo.domain.models;


import java.time.LocalDateTime;

public class MovimientoInventario {

    private long id;
    private Producto producto;
    private Sucursal origen;
    private Sucursal destino;
    private int cantidad;
    private LocalDateTime fechaMovimiento;
    private String tipoMovimiento; // "INGRESO", "SALIDA", "TRANSFERENCIA"

    public MovimientoInventario() {
    }

    public MovimientoInventario(long id, Producto producto, Sucursal origen, Sucursal destino, int cantidad, LocalDateTime fechaMovimiento, String tipoMovimiento) {
        this.id = id;
        this.producto = producto;
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
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
}

