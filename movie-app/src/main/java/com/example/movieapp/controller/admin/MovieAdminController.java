package com.example.movieapp.controller.admin;

import com.example.movieapp.model.request.MovieRequest;
import com.example.movieapp.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieAdminController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(movieService.getAllMovies(page, pageSize));
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieRequest dto) {
        return new ResponseEntity<>(movieService.createMovie(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @Valid @RequestBody MovieRequest dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
