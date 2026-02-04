package com.practica5;

import com.practica5.dao.PedidoDAO;
import com.practica5.dao.ProductoDAO;
import com.practica5.dao.UsuarioDAO;
import com.practica5.model.Producto;
import com.practica5.model.Usuario;
import com.practica5.seeders.DatabaseSeeder;
import com.practica5.util.HibernateUtil;

import java.util.List;

/**
 * Punto de entrada de la aplicación.
 * Orquesta la ejecución del seeder y demuestra el uso de HQL y CRUD.
 */
public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("=== ARRANQUE DE LA APLICACIÓN ===");

            // 1. Ejecutar seeder
            DatabaseSeeder seeder = new DatabaseSeeder();
            seeder.ejecutar();

            // 2. Instanciar DAOs
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            PedidoDAO pedidoDAO = new PedidoDAO();
            ProductoDAO productoDAO = new ProductoDAO();

            // 3. DEMOSTRACIÓN CRUD / HQL

            System.out.println("\n--- Usuarios con más de 1 pedido ---");
            List<Usuario> usuariosConPedidos =
                    usuarioDAO.obtenerUsuariosConMasDeXPedidos(1);

            usuariosConPedidos.forEach(u ->
                    System.out.println(u.getNombre() + " (" + u.getEmail() + ")")
            );

            System.out.println("\n--- Número total de pedidos ---");
            long totalPedidos = pedidoDAO.contarPedidos();
            System.out.println("Total de pedidos en el sistema: " + totalPedidos);

            System.out.println("\n--- Productos que contienen 'Portátil' ---");
            List<Producto> productos =
                    productoDAO.buscarPorNombre("Portátil");

            productos.forEach(p ->
                    System.out.println(p.getNombre() + " - " + p.getPrecio() + " €")
            );

            System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");

        } finally {
            // Cierre limpio de Hibernate
            HibernateUtil.shutdown();
        }
    }
}