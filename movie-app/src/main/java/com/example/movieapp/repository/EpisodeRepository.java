package com.example.movieapp.repository;

import com.example.movieapp.entity.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer id, Boolean status);

    Episode findByMovie_IdAndDisplayOrderAndStatus(Integer id, Integer displayOrder, Boolean status);

    Page<Episode> findByMovieId(Integer movieId, Pageable pageable);
}