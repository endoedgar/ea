package net.endoedgar.cs544.labs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Student {
    @Id
    private int studentid;
    private String firstname;
    private String lastname;

    public Student() { }

    public Student(int studentid, String firstname, String lastname) {
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentid() == student.getStudentid() &&
                Objects.equals(getFirstname(), student.getFirstname()) &&
                Objects.equals(getLastname(), student.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentid(), getFirstname(), getLastname());
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
