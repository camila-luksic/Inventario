package com.example.demo.application.dto;

public class ProductoDto {
    private Long id;
    private String nombre;
    private Long marcaId;
    private String descripcion;
    private boolean activo;
    private String foto;

    public ProductoDto(Long id, String nombre, Long marcaId, String descripcion, boolean activo, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.marcaId = marcaId;
        this.descripcion = descripcion;
        this.activo = activo;
        this.foto = foto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getMarcaId() { return marcaId; }
    public String getDescripcion() { return descripcion; }
    public boolean isActivo() { return activo; }
    public String getFoto() { return foto; }
}
