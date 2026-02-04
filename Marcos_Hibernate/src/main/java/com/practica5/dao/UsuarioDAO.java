package com.practica5.dao;

import com.practica5.model.Usuario;
import com.practica5.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * DAO para la entidad Usuario.
 */
public class UsuarioDAO {

    /**
     * Guarda un nuevo usuario en la base de datos.
     */
    public void crear(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al crear usuario", e);
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene un usuario por su ID.
     */
    public Usuario obtenerPorId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(Usuario.class, id);
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene todos los usuarios.
     */
    public List<Usuario> obtenerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        } finally {
            session.close();
        }
    }

    /**
     * Actualiza un usuario existente.
     */
    public void actualizar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(usuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar usuario", e);
        } finally {
            session.close();
        }
    }

    /**
     * Elimina un usuario.
     */
    public void eliminar(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(usuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar usuario", e);
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene los usuarios que tienen más de un número dado de pedidos.
     *
     * @param minimoPedidos número mínimo de pedidos
     * @return lista de usuarios
     */
    public List<Usuario> obtenerUsuariosConMasDeXPedidos(long minimoPedidos) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(
                            "SELECT u FROM Usuario u WHERE size(u.pedidos) > :minimo",
                            Usuario.class
                    )
                    .setParameter("minimo", minimoPedidos)
                    .list();
        } finally {
            session.close();
        }
    }
}