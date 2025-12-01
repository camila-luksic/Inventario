package com.example.demo.domain.models;

import com.example.demo.domain.valueObjects.NameValue;



public class Producto {

    private long id;
    private NameValue nombre;
    private Marca marca;
    private String descripcion;
    private boolean activo;
    private String foto;

    public Producto() {
    }

    public Producto(long id, String nombre, Marca marca, String descripcion, boolean activo, String foto) {
        this.id = id;
        this.nombre = new NameValue(nombre);
        this.marca = marca;
        this.descripcion = descripcion;
        this.activo = activo;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre.getValue();
    }

    public Marca getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getFoto() {
        return foto;
    }
}
