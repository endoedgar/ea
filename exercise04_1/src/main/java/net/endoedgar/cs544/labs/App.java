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
        bidirectionalOneToManyEmployeeLaptopSet();
        unidirectionalOneToManyPassengerFlightList();
        unidirectionalOneToManySchoolStudentMap();

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }

    private static void bidirectionalOneToManyEmployeeLaptopSet() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Employee employee1 = new Employee("Employee", "1");
            Employee employee2 = new Employee("Employee", "2");
            Laptop laptop1 = new Laptop("Acer", "i3 10th generation");

            employee1.addLaptop(laptop1);

            session.persist(employee1);
            session.persist(employee2);

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
            List<Employee> employeeList = session.createQuery("from Employee").list();
            for (Employee employee : employeeList) {
                System.out.println(employee.toString());
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

    private static void unidirectionalOneToManyPassengerFlightList() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Passenger passenger1 = new Passenger("Passenger 1");
            Passenger passenger2 = new Passenger("Passenger 2");
            Flight flight1 = new Flight(43, "SAO", "CID", new Date(119, 8, 6));
            Flight flight2 = new Flight(44, "CID", "CID", new Date(119, 8, 7));
            Flight flight3 = new Flight(45, "CID", "TOK", new Date(119, 8, 6));

            passenger1.addFlight(flight1);
            passenger2.addFlight(flight2);
            passenger2.addFlight(flight3);

            session.persist(passenger1);
            session.persist(passenger2);

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
            List<Passenger> passengerList = session.createQuery("from Passenger").list();
            for (Passenger passenger : passengerList) {
                System.out.println(passenger.toString());
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

    private static void unidirectionalOneToManySchoolStudentMap() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            School school1 = new School("School 1");
            School school2 = new School("School 2");
            Student student1 = new Student(1, "Student", "1");
            Student student2 = new Student(2, "Student", "2");
            Student student3 = new Student(3, "Student", "3");
            Student student4 = new Student(4, "Student", "4");
            Student student5 = new Student(5, "Student", "5");

            school1.addStudent(student1);
            school1.addStudent(student2);
            school2.addStudent(student3);
            school2.addStudent(student4);
            school2.addStudent(student5);

            session.persist(school1);
            session.persist(school2);

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
            List<School> schoolList = session.createQuery("from School").list();
            for (School school : schoolList) {
                System.out.println(school.toString());
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
