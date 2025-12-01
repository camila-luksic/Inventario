package com.example.demo.application.dto;

public class UsuarioDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String rol;
    private boolean activo;

    public UsuarioDto() {
    }

    public UsuarioDto(Long id, String username, String password, String email, String rol, boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public boolean isActivo() { return activo; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setRol(String rol) { this.rol = rol; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
