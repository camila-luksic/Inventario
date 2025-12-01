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

    @Column(length = 500)
    private String foto;

    protected MarcaEntity() { }

    public MarcaEntity(String nombre, String foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}
