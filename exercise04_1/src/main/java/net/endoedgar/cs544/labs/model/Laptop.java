package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String type;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Employee employee;

    public Laptop() { }

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    public Laptop(String brand, String type, Employee employee) {
        this.brand = brand;
        this.type = type;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return getId() == laptop.getId() &&
                Objects.equals(getBrand(), laptop.getBrand()) &&
                Objects.equals(getType(), laptop.getType()) &&
                getEmployee().equals(laptop.getEmployee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getType(), getEmployee());
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
