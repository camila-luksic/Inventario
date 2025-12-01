package com.example.demo.infraestructure.adapters.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "productos",
       uniqueConstraints = @UniqueConstraint(name = "uk_producto_nombre_marca", columnNames = {"nombre","marca_id"}),
       indexes = {
           @Index(name = "ix_producto_nombre", columnList = "nombre"),
           @Index(name = "ix_producto_marca", columnList = "marca_id")
       })
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_producto_marca"))
    private MarcaEntity marca;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(length = 500)
    private String foto;

    protected ProductoEntity() { }

    public ProductoEntity(String nombre, MarcaEntity marca, String descripcion, boolean activo, String foto) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.activo = activo;
        this.foto = foto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public MarcaEntity getMarca() { return marca; }
    public String getDescripcion() { return descripcion; }
    public boolean isActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMarca(MarcaEntity marca) { this.marca = marca; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}
