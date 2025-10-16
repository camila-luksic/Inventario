package com.example.demo.domain.models;


public class StockSucursal {

    private long id;
    private Sucursal sucursal;
    private Producto producto;
    private int cantidad;

    public StockSucursal() {
    }

    public StockSucursal(long id, Sucursal sucursal, Producto producto, int cantidad) {
        this.id = id;
        this.sucursal = sucursal;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
