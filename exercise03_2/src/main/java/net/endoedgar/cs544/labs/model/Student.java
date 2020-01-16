package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentid;
    private String firstname;
    private String lastname;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Course> courses = new ArrayList<>();

    public Student() { }

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getStudentid() {
        return studentid;
    }

    private void setStudentid(int studentid) {
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

    public Collection<Course> getCourses() {
        return Collections.unmodifiableCollection(courses);
    }

    public boolean addCourse(Course course) {
        return this.courses.add(course);
    }

    public boolean removeCourse(Course course) {
        return this.courses.remove(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentid() == student.getStudentid() &&
                Objects.equals(getFirstname(), student.getFirstname()) &&
                Objects.equals(getLastname(), student.getLastname()) &&
                Objects.equals(getCourses(), student.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentid(), getFirstname(), getLastname(), getCourses());
    }
}
