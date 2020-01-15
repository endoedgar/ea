package net.endoedgar.cs544.labs;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.List;

public class AppBook {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    // Hibernate placeholders
    private static Session session = null;
    private static Transaction tx = null;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) {
        persistBooks();
        listBooks();
        changeAndDeleteBook();
        listBooks();

        // Close the SessionFactory
        sessionFactory.close();
        System.exit(0);
    }

    /**
     * Change title and price of book with id 1 and delete the book with id 3
     */
    private static void changeAndDeleteBook() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Book book1 = (Book)session.load(Book.class, 1);
            book1.setTitle("Book Number 1 Changed Title");
            book1.setPrice(123);

            Book book3 = (Book)session.load(Book.class, 3);
            session.delete(book3);

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
     * List Books using a query
     */
    private static void listBooks() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("title= " + book.getTitle() +
                        ", ISBN= " + book.getISBN() +
                        ", author= " + book.getAuthor() +
                        ", price= " + book.getPrice() +
                        ", publish date= " + book.getPublish_date()
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
     * Adds 3 sample Books
     */
    private static void persistBooks() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Book book1 = new Book("Book Number 1", "ISBN 1", "Book Author 1", 100, new Date(120, 11, 1));
            Book book2 = new Book("Book Number 2", "ISBN 2", "Book Author 2", 200, new Date(120, 11, 2));
            Book book3 = new Book("Book Number 3", "ISBN 3", "Book Author 3", 300, new Date(120, 11, 3));

            // save the books
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);

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
