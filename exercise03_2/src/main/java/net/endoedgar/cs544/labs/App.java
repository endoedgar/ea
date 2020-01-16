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
        //bidirectionalOneToManyDeptEmployee();
        //optionalUnidirectionalManyToOneBookPublisher();
        bidirectionalManyToManyStudentCourse();
        //unidirectionalOneToManyCustomerReservation();
        unidirectionalManyToOneReservationBook();
        bidirectionalManyToOneEmployeeOffice();

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }

    private static void bidirectionalManyToOneEmployeeOffice() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Office office1 = new Office(11,"Main Building");
            Office office2 = new Office(10,"Back Building");
            Department department1 = new Department("Faculty");
            Employee employee1 = new Employee("Faculty Member 1");
            Employee employee2 = new Employee("Faculty Member 2");
            department1.addEmployee(employee1);
            department1.addEmployee(employee2);

            office1.addEmployee(employee1);
            office1.addEmployee(employee2);

            Department department2 = new Department("Human Resources");
            Employee employee3 = new Employee("HR Member 1");
            Employee employee4 = new Employee("HR Member 2");
            department2.addEmployee(employee3);
            department2.addEmployee(employee4);

            office2.addEmployee(employee3);
            office2.addEmployee(employee4);

            session.persist(department1);
            session.persist(department2);

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
            List<Department> departmentList = session.createQuery("from Department").list();
            for (Department department : departmentList) {
                System.out.println(department.toString());
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

    private static void unidirectionalManyToOneReservationBook() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Customer customer1 = new Customer("Customer 1");
            Book book1 = new Book("1111", "Book 1", "Author 1", null);
            Reservation reservation1 = new Reservation(new Date(120, 2, 1), book1);
            Reservation reservation2 = new Reservation(new Date(120, 2, 2), book1);
            customer1.addReservation(reservation1);
            customer1.addReservation(reservation2);

            Customer customer2 = new Customer("Customer 2");
            Reservation reservation3 = new Reservation(new Date(120, 2, 1), book1);
            customer2.addReservation(reservation3);

            Customer customer3 = new Customer("Customer 3");

            session.persist(customer1);
            session.persist(customer2);
            session.persist(customer3);

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

    private static void unidirectionalOneToManyCustomerReservation() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Customer customer1 = new Customer("Customer 1");
            Reservation reservation1 = new Reservation(new Date(120, 2, 1));
            Reservation reservation2 = new Reservation(new Date(120, 2, 2));
            customer1.addReservation(reservation1);
            customer1.addReservation(reservation2);

            Customer customer2 = new Customer("Customer 2");
            Reservation reservation3 = new Reservation(new Date(120, 2, 1));
            customer2.addReservation(reservation3);

            Customer customer3 = new Customer("Customer 3");

            session.persist(customer1);
            session.persist(customer2);
            session.persist(customer3);

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

    private static void bidirectionalOneToManyDeptEmployee() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Department department1 = new Department("Faculty");
            Employee employee1 = new Employee("Faculty Member 1");
            Employee employee2 = new Employee("Faculty Member 2");
            department1.addEmployee(employee1);
            department1.addEmployee(employee2);

            Department department2 = new Department("Human Resources");
            Employee employee3 = new Employee("HR Member 1");
            Employee employee4 = new Employee("HR Member 2");
            department2.addEmployee(employee3);
            department2.addEmployee(employee4);

            session.persist(department1);
            session.persist(department2);

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
            List<Department> departmentList = session.createQuery("from Department").list();
            for (Department department : departmentList) {
                System.out.println(department.toString());
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

    private static void optionalUnidirectionalManyToOneBookPublisher() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Publisher publisher1 = new Publisher("Publisher 1");
            Book book1 = new Book("111111", "Book 1", "Book 1 Author", publisher1);
            Book book2 = new Book("111112", "Book 2", "Book 2 Author", null);

            Publisher publisher2 = new Publisher("Publisher 2");
            session.persist(publisher2);

            session.persist(book1);
            session.persist(book2);

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
            List<Publisher> publisherList = session.createQuery("from Publisher").list();
            for (Publisher publisher : publisherList) {
                System.out.println(publisher.toString());
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

    private static void bidirectionalManyToManyStudentCourse() {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Course course1 = new Course("CS458", "Algorithms");
            Student student1 = new Student("Unfortunate", "Student 1");
            Student student2 = new Student("Unfortunate", "Student 2");
            course1.addStudent(student1);
            course1.addStudent(student2);

            Course course2 = new Course("CS544", "Enterprise Architecture");
            Student student3 = new Student("Fortunate", "Student 3");
            Student student4 = new Student("Fortunate", "Student 4");
            course2.addStudent(student3);
            course2.addStudent(student4);

            session.persist(course1);
            session.persist(course2);

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
            List<Course> courseList = session.createQuery("from Course").list();
            for (Course course : courseList) {
                System.out.println(course.toString());
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
