package com.practica5.dao;

import com.practica5.model.Producto;
import com.practica5.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO para la entidad Producto.
 */
public class ProductoDAO {

    public void crear(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(producto);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al crear producto", e);
        } finally {
            session.close();
        }
    }

    public Producto obtenerPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Producto.class, id);
        } finally {
            session.close();
        }
    }

    public List<Producto> obtenerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Producto", Producto.class).list();
        } finally {
            session.close();
        }
    }

    public void actualizar(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(producto);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar producto", e);
        } finally {
            session.close();
        }
    }

    public void eliminar(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(producto);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar producto", e);
        } finally {
            session.close();
        }
    }
    /**
     * Busca productos cuyo nombre contenga un texto dado.
     *
     * @param texto texto a buscar
     * @return lista de productos coincidentes
     */
    public List<Producto> buscarPorNombre(String texto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "FROM Producto p WHERE p.nombre LIKE :texto",
                            Producto.class
                    )
                    .setParameter("texto", "%" + texto + "%")
                    .list();
        } finally {
            session.close();
        }
    }
}
