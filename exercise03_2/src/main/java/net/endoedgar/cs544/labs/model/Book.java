package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Book {
    @Id
    @Column(length = 13)
    private String isbn;
    private String title;
    private String author;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinTable(name="book_publisher")
    private Publisher publisher;

    public Book() { }

    public Book(String isbn, String title, String author, Publisher publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher=" + publisher +
                '}';
    }
}
