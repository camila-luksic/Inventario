package com.example.demo.domain.models;


public class Marca {

    private long id;
    private String nombre;
    private String foto;

    public Marca() {
    }

    public Marca(long id, String nombre, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }
}
