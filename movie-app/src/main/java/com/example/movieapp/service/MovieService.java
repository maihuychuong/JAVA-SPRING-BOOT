package com.example.movieapp.service;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.request.MovieRequest;
import com.example.movieapp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    public List<Movie> findHotMovie(Boolean status, Integer limit) {
        return movieRepository.findHotMovie(status, limit);
    }

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type, status, pageable);
        return moviePage;
    }

    public Movie findMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true);
    }



    public Page<Movie> getAllMovies(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findAll(pageable);
    }

    public Movie createMovie(MovieRequest dto) {
        Movie movie = convertToEntity(dto, new Movie());
        movie.setCreatedAt(LocalDateTime.now());
        movie.setUpdatedAt(LocalDateTime.now());
        movie.setRating(5.0); // default rating
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, MovieRequest dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim có id = " + id));
        convertToEntity(dto, movie);
        movie.setUpdatedAt(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy phim có id = " + id);
        }
        movieRepository.deleteById(id);
    }

    private Movie convertToEntity(MovieRequest dto, Movie movie) {
        movie.setName(dto.getName());
        movie.setTrailer(dto.getTrailer());
        movie.setDescription(dto.getDescription());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setType(dto.getType());
        movie.setStatus(dto.getStatus());

        movie.setCountry(countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy quốc gia")));

        if (dto.getGenreIds() != null)
            movie.setGenres(genreRepository.findAllById(dto.getGenreIds()));

        if (dto.getActorIds() != null)
            movie.setActors(actorRepository.findAllById(dto.getActorIds()));

        if (dto.getDirectorIds() != null)
            movie.setDirectors(directorRepository.findAllById(dto.getDirectorIds()));

        return movie;
    }
}
