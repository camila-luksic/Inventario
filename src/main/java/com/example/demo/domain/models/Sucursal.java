package com.example.demo.domain.models;


public class Sucursal {

    private long id;
    private String nombre;
    private String direccion;
    private String horarioApertura;
    private String horarioCierre;

    public Sucursal() {
    }

    public Sucursal(long id, String nombre, String direccion, String horarioApertura, String horarioCierre) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }
}
