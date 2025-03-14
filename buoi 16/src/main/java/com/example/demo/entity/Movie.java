package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer releaseYear;
    private Integer duration;
    private Float rating;

    @Column(length = 255)
    private String director;
    private String posterUrl;
    private String trailerUrl;
    private String videoUrl;

    @OneToMany(mappedBy = "movie")
    private Set<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private Set<MovieActor> movieActors;

    // Getters và Setters
}
