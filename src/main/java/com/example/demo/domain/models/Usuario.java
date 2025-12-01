package com.example.demo.domain.models;

import com.example.demo.domain.valueObjects.NameValue;

public class Usuario {

    private long id;
    private NameValue username;
    private String password;
    private String email;
    private String rol;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(long id, String username, String password, String email, String rol, boolean activo) {
        this.id = id;
        this.username = new NameValue(username);
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username.getValue();
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }
}
