package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @OneToMany(targetEntity = Student.class)
    private List<Student> students;

    public Classroom(){

    }

    public Classroom(String name, String description, List<Student> students) {
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
