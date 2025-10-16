package com.example.demo.infraestructure.adapters.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "sucursales",
       uniqueConstraints = @UniqueConstraint(name = "uk_sucursal_nombre", columnNames = {"nombre"}),
       indexes = @Index(name = "ix_sucursal_nombre", columnList = "nombre"))
public class SucursalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=150)
    private String nombre;

    @Column(nullable=false, length=250)
    private String direccion;

    protected SucursalEntity() { }

    public SucursalEntity(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
