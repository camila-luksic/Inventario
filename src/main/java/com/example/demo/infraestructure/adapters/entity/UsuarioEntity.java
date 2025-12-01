package com.example.demo.infraestructure.adapters.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios",
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_usuario_username", columnNames = {"username"}),
           @UniqueConstraint(name = "uk_usuario_email", columnNames = {"email"})
       },
       indexes = {
           @Index(name = "ix_usuario_username", columnList = "username"),
           @Index(name = "ix_usuario_email", columnList = "email")
       })
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String rol = "USER";

    @Column(nullable = false)
    private boolean activo = true;

    protected UsuarioEntity() { }

    public UsuarioEntity(String username, String password, String email, String rol, boolean activo) {
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

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setRol(String rol) { this.rol = rol; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
