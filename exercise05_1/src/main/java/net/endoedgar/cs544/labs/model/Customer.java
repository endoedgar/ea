package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private Collection<Order> orders = new ArrayList<>();

    public Customer() { }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Collection<Order> getOrders() {
        return Collections.unmodifiableCollection(orders);
    }

    public boolean addOrder(Order order) {
        order.setCustomer(this);
        return this.orders.add(order);
    }

    public boolean removeOrder(Order order) {
        order.setCustomer(null);
        return this.orders.remove(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", orders=" + orders +
                '}';
    }
}
