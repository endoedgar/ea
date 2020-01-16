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
        persistAndShowCustomers();

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }

    private static void persistAndShowCustomers() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Customer customer1 = new Customer("Customer", "1");
            Customer customer2 = new Customer("Customer", "2");
            Order order1 = new Order(new Date(120, 1, 2));
            order1.addOrderLine(new OrderLine(2,
                    new Product("Some Product 1", "Description for product 1")
            ));
            order1.addOrderLine(new OrderLine(2,
                    new CD("Some CD 1", "Description for cd 1","Artist for cd 1")
            ));
            order1.addOrderLine(new OrderLine(1,
                    new DVD("Some DVD 1", "Description for dvd 1","Genre for dvd 1")
            ));
            order1.addOrderLine(new OrderLine(4,
                    new Book("Some BOOK 1", "Description for book 1","Title for book 1")
            ));
            customer1.addOrder(order1);

            session.persist(customer1);
            session.persist(customer2);

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
            List<Customer> customerList = session.createQuery("from Customer").list();
            for (Customer customer : customerList) {
                System.out.println(customer.toString());
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
