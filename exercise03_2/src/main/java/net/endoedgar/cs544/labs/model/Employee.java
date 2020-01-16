package net.endoedgar.cs544.labs.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeenumber;
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Department department;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Office office;

    public Employee() { }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Department department, Office office) {
        this.name = name;
        this.department = department;
        this.office = office;
    }

    public int getEmployeenumber() { return employeenumber; }

    private void setEmployeenumber(int employeenumber) { this.employeenumber = employeenumber; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Department getDepartment() { return department; }

    public void setDepartment(Department department) { this.department = department; }

    public Office getOffice() { return office; }

    public void setOffice(Office office) { this.office = office; }

    @Override
    public String toString() {
        return "Employee{" +
                "employeenumber=" + employeenumber +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", office=" + office +
                '}';
    }
}
