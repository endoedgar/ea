package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private Collection<Employee> employees = new ArrayList<>();

    public Department() { }

    public Department(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    private void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Collection<Employee> getEmployees() { return Collections.unmodifiableCollection(employees); }

    public boolean addEmployee(Employee employee) {
        employee.setDepartment(this);
        return this.employees.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        employee.setDepartment(null);
        return this.employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
