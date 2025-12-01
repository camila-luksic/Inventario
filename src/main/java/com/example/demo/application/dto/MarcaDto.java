package com.example.demo.application.dto;

public class MarcaDto {
    private Long id;
    private String nombre;
    private String foto;

    public MarcaDto(Long id, String nombre, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getFoto() { return foto; }
}
