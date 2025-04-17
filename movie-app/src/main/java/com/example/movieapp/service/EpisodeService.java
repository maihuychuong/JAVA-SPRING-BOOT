package com.example.movieapp.service;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.exception.NotFoundException;
import com.example.movieapp.model.request.EpisodeRequest;
import com.example.movieapp.repository.EpisodeRepository;
import com.example.movieapp.repository.MovieRepository;
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
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;

    public List<Episode> findEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id, true);
    }

    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        // select * from episodes where movie_id = id and display_order = displayOrder and status = true
        return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }

    public Page<Episode> getEpisodes(Integer movieId, int page, int pageSize) {
        if (!movieRepository.existsById(movieId)) {
            throw new NotFoundException("Không tìm thấy phim với id = " + movieId);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("displayOrder").ascending());
        return episodeRepository.findByMovieId(movieId, pageable);
    }

    public Episode create(EpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim với id = " + request.getMovieId()));

        Episode episode = Episode.builder()
                .name(request.getName())
                .duration(request.getDuration())
                .displayOrder(request.getDisplayOrder())
                .videoUrl(request.getVideoUrl())
                .status(request.getStatus())
                .movie(movie)
                .createdAt(LocalDateTime.now())
                .build();

        return episodeRepository.save(episode);
    }

    public Episode update(Integer id, EpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim với id = " + id));

        episode.setName(request.getName());
        episode.setDuration(request.getDuration());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setVideoUrl(request.getVideoUrl());
        episode.setStatus(request.getStatus());
        episode.setUpdatedAt(LocalDateTime.now());

        return episodeRepository.save(episode);
    }

    public void delete(Integer id) {
        if (!episodeRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy tập phim với id = " + id);
        }
        episodeRepository.deleteById(id);
    }
}
