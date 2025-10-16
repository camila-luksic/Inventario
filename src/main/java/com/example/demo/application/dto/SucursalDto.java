package com.example.demo.application.dto;

public class SucursalDto {
    private Long id;
    private String nombre;
    private String direccion;

    public SucursalDto(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
}
