package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Teacher {

    @Id
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "teacher")
//    private List<TeacherStudent> teacherStudentList;

    @ManyToMany
    private List<Student> studentList;
}
