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
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<Reservation> reservations = new ArrayList<>();

    public Customer() { }

    public Customer(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Reservation> getReservations() {
        return Collections.unmodifiableCollection(reservations);
    }

    public boolean addReservation(Reservation reservation) {
        return this.reservations.add(reservation);
    }

    public boolean removeReservation(Reservation reservation) {
        return this.reservations.remove(reservation);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
