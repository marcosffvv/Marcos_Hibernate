package com.practica5.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa una categoría de productos.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    /**
     * Relación N:N con Producto.
     */
    @ManyToMany(mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<>();

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Producto> getProductos() {
        return productos;
    }
}
