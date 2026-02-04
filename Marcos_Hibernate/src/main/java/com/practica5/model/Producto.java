package com.practica5.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa un producto de la tienda.
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    private double precio;

    /**
     * Relación N:N con Pedido.
     */
    @ManyToMany(mappedBy = "productos")
    private Set<Pedido> pedidos = new HashSet<>();

    /**
     * Relación N:N con Categoria.
     */
    @ManyToMany
    @JoinTable(
        name = "producto_categoria",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    public Producto() {
    }

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * Asocia una categoría al producto.
     */
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
        categoria.getProductos().add(this);
    }
}
