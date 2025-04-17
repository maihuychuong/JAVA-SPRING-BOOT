package com.example.movieapp.controller.admin;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.model.request.EpisodeRequest;
import com.example.movieapp.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeAdminController {
    private final EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<Page<Episode>> getEpisodes(
            @RequestParam(required = false) Integer movieId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        if (movieId == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(episodeService.getEpisodes(movieId, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Episode> create(@Valid @RequestBody EpisodeRequest request) {
        return new ResponseEntity<>(episodeService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> update(
            @PathVariable Integer id,
            @Valid @RequestBody EpisodeRequest request
    ) {
        return ResponseEntity.ok(episodeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        episodeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
