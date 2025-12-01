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

    @Column(length = 100)
    private String horarioApertura;

    @Column(length = 100)
    private String horarioCierre;

    protected SucursalEntity() { }

    public SucursalEntity(String nombre, String direccion, String horarioApertura, String horarioCierre) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getHorarioApertura() { return horarioApertura; }
    public void setHorarioApertura(String horarioApertura) { this.horarioApertura = horarioApertura; }
    public String getHorarioCierre() { return horarioCierre; }
    public void setHorarioCierre(String horarioCierre) { this.horarioCierre = horarioCierre; }
}
