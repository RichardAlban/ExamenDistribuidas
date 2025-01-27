package com.espe.micro_categorias.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")  // Nombre de la tabla, puede omitirse si coincide con el nombre de la clase
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(max = 63, min = 2, message = "El nombre debe tener entre 2 y 63 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CategoriaProducto> categoriaProductos = new ArrayList<>();

    // Establece automáticamente el valor de created_at antes de persistir
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public List<CategoriaProducto> getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(List<CategoriaProducto> categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }
}
