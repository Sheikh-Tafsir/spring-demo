package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @NotNull
    @Column(nullable = false, length = 32, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false, length = 32)
    private String password;

    @OneToOne(mappedBy = "user")
    private Passport passport;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos;

//    @ManyToMany
//    private List<Teacher> teacherList;
}
