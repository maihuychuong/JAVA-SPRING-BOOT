package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String birthdate;
    private String photoUrl;

    @OneToMany(mappedBy = "actor")
    private Set<MovieActor> movieActors;

    // Getters và Setters
}
