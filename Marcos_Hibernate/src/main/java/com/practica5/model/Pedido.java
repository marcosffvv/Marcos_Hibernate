package com.practica5.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa un pedido realizado por un usuario.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime fecha;

    /**
     * Relación N:1 con Usuario.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /**
     * Relación N:N con Producto.
     */
    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos = new HashSet<>();

    public Pedido() {
        this.fecha = LocalDateTime.now();
    }

    public Pedido(Usuario usuario) {
        this.usuario = usuario;
        this.fecha = LocalDateTime.now();
    }

    // Getters

    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Añade un producto al pedido.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.getPedidos().add(this);
    }
}
