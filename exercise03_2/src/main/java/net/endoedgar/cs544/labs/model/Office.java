package net.endoedgar.cs544.labs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Office {
    @Id
    private int roomnumber;
    private String building;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private Collection<Employee> employees = new ArrayList<>();

    public Office() { }

    public Office(int roomnumber, String building) {
        this.roomnumber = roomnumber;
        this.building = building;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    private void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees);
    }

    public void addEmployee(Employee employee) {
        employee.setOffice(this);
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employee.setOffice(null);
        this.employees.remove(employee);
    }
}
