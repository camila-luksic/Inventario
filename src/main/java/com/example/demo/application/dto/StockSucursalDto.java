package com.example.demo.application.dto;

public class StockSucursalDto {
    private Long id;
    private Long sucursalId;
    private Long productoId;
    private int cantidad;

    public StockSucursalDto(Long id, Long sucursalId, Long productoId, int cantidad) {
        this.id = id;
        this.sucursalId = sucursalId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public Long getSucursalId() { return sucursalId; }
    public Long getProductoId() { return productoId; }
    public int getCantidad() { return cantidad; }
}
