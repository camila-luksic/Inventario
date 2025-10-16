package com.example.demo.domain.models;

public class LoteSucursal {

    private long id;
    private Lote lote;
    private Sucursal sucursal;
    private int cantidad;

    public LoteSucursal() {
    }

    public LoteSucursal(long id, Lote lote, Sucursal sucursal, int cantidad) {
        this.id = id;
        this.lote = lote;
        this.sucursal = sucursal;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public Lote getLote() {
        return lote;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public int getCantidad() {
        return cantidad;
    }
}
