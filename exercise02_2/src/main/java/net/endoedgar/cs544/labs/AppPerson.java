package net.endoedgar.cs544.labs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.List;

public class AppPerson {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    // Hibernate placeholders
    private static Session session = null;
    private static Transaction tx = null;

    static {
        // Create ServiceRegistry
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static void main(String[] args) {
        persistPersons();
        listPersons();
        changeAndDeletePersons();
        listPersons();

        // Close the SessionFactory
        sessionFactory.close();
        System.exit(0);
    }

    /**
     * Change first name of person 1 and delete the person with id 3
     */
    private static void changeAndDeletePersons() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Person person1 = (Person)session.load(Person.class, 1);
            person1.setFirstname("First Name 1 Changed");

            Person person3 = (Person)session.load(Person.class, 3);
            session.delete(person3);

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

    /**
     * List Persons using a query
     */
    private static void listPersons() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            @SuppressWarnings("unchecked")
            List<Person> personList = session.createQuery("from Person").list();
            for (Person person : personList) {
                System.out.println("firstName= " + person.getFirstname() +
                        ", lastName= " + person.getLastname() +
                        ", dateOfBirth= " + person.getDateofbirth()
                );
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

    /**
     * Adds 3 sample Persons
     */
    private static void persistPersons() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Person person1 = new Person("First Name 1", "LastName 1", new Date(90, 1, 1));
            Person person2 = new Person("First Name 2", "LastName 2", new Date(90, 1, 2));
            Person person3 = new Person("First Name 3", "LastName 3", new Date(90, 1, 3));

            // save the books
            session.persist(person1);
            session.persist(person2);
            session.persist(person3);

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