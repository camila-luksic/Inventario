package com.example.demo.application.dto;

public class ProductoDto {
    private Long id;
    private String nombre;
    private Long marcaId;
    private String descripcion;
    private boolean activo;

    public ProductoDto(Long id, String nombre, Long marcaId, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.marcaId = marcaId;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getMarcaId() { return marcaId; }
    public String getDescripcion() { return descripcion; }
    public boolean isActivo() { return activo; }
}
