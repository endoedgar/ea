package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseNumber;
    private String name;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST)
    private Collection<Student> students = new ArrayList<>();

    public Course() { }

    public Course(String courseNumber, String name) {
        this.courseNumber = courseNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Student> getStudents() {
        return Collections.unmodifiableCollection(students);
    }

    public boolean addStudent(Student student) {
        student.addCourse(this);
        return this.students.add(student);
    }

    public boolean removeStudent(Student student) {
        student.removeCourse(this);
        return this.students.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNumber='" + courseNumber + '\'' +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() &&
                getCourseNumber().equals(course.getCourseNumber()) &&
                Objects.equals(getName(), course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseNumber(), getName());
    }
}
