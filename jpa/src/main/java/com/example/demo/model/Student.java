package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

//    @OneToMany(mappedBy = "student")
//    private List<TeacherStudent> teacherStudentList;

    @ManyToMany
    @JoinTable(
            name = "teacher_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teacherList;
}
