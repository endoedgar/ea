package net.endoedgar.cs544.labs;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class AppCar {


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
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Owner owner1 = new Owner("Owner 1","Owner address 1");
            Car car1 = new Car("BMW", "SDA231", 30221.00, owner1);
            session.persist(car1);
            Owner owner2 = new Owner("Owner 2","Owner address 2");
            Car car2 = new Car("Mercedes", "HOO100", 4088.00, owner2);
            session.persist(car2);

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

            // retrieve all cars and their owners
            @SuppressWarnings("unchecked")
            List<Car> carList = session.createQuery("from Car").list();
            for (Car car : carList) {
                System.out.println("brand= " + car.getBrand() +
                        ", year= " + car.getYear() +
                        ", price= " + car.getPrice() +
                        ", owner.id= " + car.getOwner().getId() +
                        ", owner.name= " + car.getOwner().getName() +
                        ", owner.address= " + car.getOwner().getAddress()
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

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
