package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_actors")
public class MovieActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

    @Column(length = 100)
    private String role;

    // Getters và Setters
}
