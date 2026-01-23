package com.practica5;

import com.practica5.model.Usuario;
import com.practica5.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Ejemplo básico de uso de Hibernate
 */
public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Usuario u1 = new Usuario("Juan", "juan@email.com");
            session.persist(u1);

            session.getTransaction().commit();
            System.out.println("Usuario guardado correctamente");

            Usuario recuperado = session.get(Usuario.class, u1.getId());
            System.out.println(
                    "Recuperado: " +
                    recuperado.getNombre() + " - " +
                    recuperado.getEmail()
            );

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
