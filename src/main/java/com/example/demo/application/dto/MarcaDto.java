package com.example.demo.application.dto;

public class MarcaDto {
    private Long id;
    private String nombre;

    public MarcaDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
}
