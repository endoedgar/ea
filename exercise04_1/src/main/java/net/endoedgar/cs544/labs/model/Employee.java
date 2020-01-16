package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Laptop> laptops = new HashSet<>();

    public Employee() { }

    public Employee(String firstname, String lastname) {
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

    public Set<Laptop> getLaptops() {
        return Collections.unmodifiableSet(laptops);
    }

    public boolean addLaptop(Laptop laptop) {
        laptop.setEmployee(this);
        return this.laptops.add(laptop);
    }

    public boolean removeLaptop(Laptop laptop) {
        laptop.setEmployee(null);
        return this.laptops.remove(laptop);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", laptops=" + laptops +
                '}';
    }
}
