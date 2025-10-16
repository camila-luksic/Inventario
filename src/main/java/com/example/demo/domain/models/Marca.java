package com.example.demo.domain.models;


public class Marca {

    private long id;
    private String nombre;

    public Marca() {
    }

    public Marca(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
