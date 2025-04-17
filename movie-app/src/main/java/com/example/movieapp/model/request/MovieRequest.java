package com.example.movieapp.model.request;

import com.example.movieapp.model.enums.MovieType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieRequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String trailer;

    @NotEmpty
    private String description;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private MovieType type;

    @NotNull
    private Boolean status;

    @NotNull
    private Long countryId;

    private List<Long> genreIds;
    private List<Long> actorIds;
    private List<Long> directorIds;
}