package com.practica5.dao;

import com.practica5.model.Pedido;
import com.practica5.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO para la entidad Pedido.
 */
public class PedidoDAO {

    public void crear(Pedido pedido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(pedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al crear pedido", e);
        } finally {
            session.close();
        }
    }

    public Pedido obtenerPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Pedido.class, id);
        } finally {
            session.close();
        }
    }

    public List<Pedido> obtenerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Pedido", Pedido.class).list();
        } finally {
            session.close();
        }
    }

    public void actualizar(Pedido pedido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(pedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar pedido", e);
        } finally {
            session.close();
        }
    }

    public void eliminar(Pedido pedido) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(pedido);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar pedido", e);
        } finally {
            session.close();
        }
    }
    /**
     * Devuelve el número total de pedidos registrados.
     *
     * @return total de pedidos
     */
    public long contarPedidos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                    "SELECT COUNT(p) FROM Pedido p",
                    Long.class
            ).getSingleResult();
        } finally {
            session.close();
        }
    }
}
