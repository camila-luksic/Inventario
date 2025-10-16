package com.example.demo.domain.models;


public class Sucursal {

    private long id;
    private String nombre;
    private String direccion;

    public Sucursal() {
    }

    public Sucursal(long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
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
}
