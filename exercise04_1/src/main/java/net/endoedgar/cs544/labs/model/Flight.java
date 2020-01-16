package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int flightNumber;
    @Column(name = "fromColumn")
    private String from;
    @Column(name = "toColumn")
    private String to;
    private Date date;

    public Flight() { }

    public Flight(int flightNumber, String from, String to, Date date) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() &&
                getFlightNumber() == flight.getFlightNumber() &&
                Objects.equals(getFrom(), flight.getFrom()) &&
                Objects.equals(getTo(), flight.getTo()) &&
                Objects.equals(getDate(), flight.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFlightNumber(), getFrom(), getTo(), getDate());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
