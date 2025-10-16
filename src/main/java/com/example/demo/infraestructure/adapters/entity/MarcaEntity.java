package com.example.demo.infraestructure.adapters.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "marcas",
       uniqueConstraints = @UniqueConstraint(name = "uk_marca_nombre", columnNames = {"nombre"}),
       indexes = @Index(name = "ix_marca_nombre", columnList = "nombre"))
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    protected MarcaEntity() { }

    public MarcaEntity(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
