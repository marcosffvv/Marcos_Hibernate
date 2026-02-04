package com.practica5.seeders;

import com.practica5.dao.PedidoDAO;
import com.practica5.dao.ProductoDAO;
import com.practica5.dao.UsuarioDAO;
import com.practica5.model.*;

import java.util.List;

/**
 * Clase encargada de poblar la base de datos con datos iniciales.
 */
public class DatabaseSeeder {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    /**
     * Ejecuta la carga inicial de datos.
     */
    public void ejecutar() {

        System.out.println("=== Iniciando DatabaseSeeder ===");

        // ---------- CATEGORÍAS ----------
        Categoria tecnologia = new Categoria("Tecnología");
        Categoria hogar = new Categoria("Hogar");
        Categoria oficina = new Categoria("Oficina");

        // ---------- PRODUCTOS ----------
        Producto p1 = new Producto("Portátil Lenovo", 899.99);
        Producto p2 = new Producto("Ratón Logitech", 24.99);
        Producto p3 = new Producto("Teclado Mecánico", 79.99);
        Producto p4 = new Producto("Monitor 24 pulgadas", 199.99);
        Producto p5 = new Producto("Silla de Oficina", 149.99);
        Producto p6 = new Producto("Lámpara LED", 29.99);
        Producto p7 = new Producto("Impresora HP", 129.99);
        Producto p8 = new Producto("Tablet Samsung", 249.99);
        Producto p9 = new Producto("Auriculares Sony", 99.99);
        Producto p10 = new Producto("Disco SSD 1TB", 119.99);

        // Asociar productos a categorías
        p1.agregarCategoria(tecnologia);
        p2.agregarCategoria(tecnologia);
        p3.agregarCategoria(oficina);
        p4.agregarCategoria(tecnologia);
        p5.agregarCategoria(oficina);
        p6.agregarCategoria(hogar);
        p7.agregarCategoria(oficina);
        p8.agregarCategoria(tecnologia);
        p9.agregarCategoria(tecnologia);
        p10.agregarCategoria(tecnologia);

        // Guardar productos (cascada guarda categorías)
        List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
                .forEach(productoDAO::crear);

        System.out.println("Productos y categorías creados");

        // ---------- USUARIOS ----------
        Usuario u1 = new Usuario("Ana López", "ana@email.com");
        Usuario u2 = new Usuario("Carlos Ruiz", "carlos@email.com");
        Usuario u3 = new Usuario("Laura Gómez", "laura@email.com");
        Usuario u4 = new Usuario("David Martín", "david@email.com");
        Usuario u5 = new Usuario("María Pérez", "maria@email.com");
        Usuario u6 = new Usuario("Jorge Sánchez", "jorge@email.com");
        Usuario u7 = new Usuario("Lucía Torres", "lucia@email.com");
        Usuario u8 = new Usuario("Pablo Romero", "pablo@email.com");
        Usuario u9 = new Usuario("Elena Díaz", "elena@email.com");
        Usuario u10 = new Usuario("Raúl Navarro", "raul@email.com");

        List.of(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10)
                .forEach(usuarioDAO::crear);

        System.out.println("Usuarios creados");

        // ---------- PEDIDOS ----------
        Pedido pedido1 = new Pedido(u1);
        pedido1.agregarProducto(p1);
        pedido1.agregarProducto(p2);

        Pedido pedido2 = new Pedido(u2);
        pedido2.agregarProducto(p3);
        pedido2.agregarProducto(p5);

        Pedido pedido3 = new Pedido(u3);
        pedido3.agregarProducto(p4);
        pedido3.agregarProducto(p10);

        Pedido pedido4 = new Pedido(u4);
        pedido4.agregarProducto(p6);

        Pedido pedido5 = new Pedido(u5);
        pedido5.agregarProducto(p7);
        pedido5.agregarProducto(p3);

        Pedido pedido6 = new Pedido(u6);
        pedido6.agregarProducto(p8);
        pedido6.agregarProducto(p9);

        Pedido pedido7 = new Pedido(u7);
        pedido7.agregarProducto(p2);

        Pedido pedido8 = new Pedido(u8);
        pedido8.agregarProducto(p1);
        pedido8.agregarProducto(p10);

        Pedido pedido9 = new Pedido(u9);
        pedido9.agregarProducto(p5);

        Pedido pedido10 = new Pedido(u10);
        pedido10.agregarProducto(p4);
        pedido10.agregarProducto(p9);

        List.of(
                pedido1, pedido2, pedido3, pedido4, pedido5,
                pedido6, pedido7, pedido8, pedido9, pedido10
        ).forEach(pedidoDAO::crear);

        System.out.println("Pedidos creados");

        System.out.println("=== DatabaseSeeder finalizado ===");
    }
}
