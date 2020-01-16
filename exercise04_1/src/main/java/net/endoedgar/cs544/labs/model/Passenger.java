package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Passenger_id")
    @OrderColumn(name = "sequence")
    private List<Flight> flights = new ArrayList<>();

    public Passenger() { }

    public Passenger(String name) {
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

    public List<Flight> getFlights() {
        return Collections.unmodifiableList(flights);
    }

    public boolean addFlight(Flight flight) {
        return this.flights.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return this.flights.remove(flight);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }
}
