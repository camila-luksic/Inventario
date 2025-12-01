package com.example.demo.application.dto;

public class SucursalDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String horarioApertura;
    private String horarioCierre;

    public SucursalDto(Long id, String nombre, String direccion, String horarioApertura, String horarioCierre) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getHorarioApertura() { return horarioApertura; }
    public String getHorarioCierre() { return horarioCierre; }
}
