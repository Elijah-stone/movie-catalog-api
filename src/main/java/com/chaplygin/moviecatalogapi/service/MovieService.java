package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.entity.Movie;
import com.chaplygin.moviecatalogapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }
}
