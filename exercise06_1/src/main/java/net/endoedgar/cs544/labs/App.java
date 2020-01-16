package net.endoedgar.cs544.labs;

import java.util.Date;
import java.util.List;

import net.endoedgar.cs544.labs.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class App {


    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        // Have to update this part to work with the version 5.2.18.Final of Hibernate
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void main(String[] args) {
        persistAndShowAppointments();

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }

    private static void persistAndShowAppointments() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Appointment appointment1 = new Appointment("15/05/08",
                    new Patient("John Doe", "100 Main Street", "23114", "Boston"),
                    new Payment("12/06/08", 100),
                    new Doctor("Eye doctor", "Frank", "Brown")
            );

            session.persist(appointment1);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Appointment> appointmentList = session.createQuery("from Appointment").list();
            for (Appointment appointment : appointmentList) {
                System.out.println(appointment.toString());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
