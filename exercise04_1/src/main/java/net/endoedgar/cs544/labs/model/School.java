package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "School_id")
    @MapKey(name = "studentid")
    private Map<Integer, Student> studentMap = new HashMap<>();

    public School() { }

    public School(String name) {
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

    public Map<Integer, Student> getStudentMap() {
        return Collections.unmodifiableMap(studentMap);
    }

    public Student addStudent(Student student) {
        return this.studentMap.put(student.getStudentid(), student);
    }

    public Student removeStudent(Student student) {
        return this.studentMap.remove(student.getStudentid());
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentMap=" + studentMap +
                '}';
    }
}
