package com.example.demo.application.dto;

public class LoteSucursalDto {
    private Long id;
    private Long loteId;
    private Long sucursalId;
    private int cantidad;

    public LoteSucursalDto(Long id, Long loteId, Long sucursalId, int cantidad) {
        this.id = id;
        this.loteId = loteId;
        this.sucursalId = sucursalId;
        this.cantidad = cantidad;
    }

    public Long getId() { return id; }
    public Long getLoteId() { return loteId; }
    public Long getSucursalId() { return sucursalId; }
    public int getCantidad() { return cantidad; }
}
