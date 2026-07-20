package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.entity.Movie;
import com.chaplygin.moviecatalogapi.exception.MovieNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.MovieMapper;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
import com.chaplygin.moviecatalogapi.repository.GenreRepository;
import com.chaplygin.moviecatalogapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository,
                        GenreRepository genreRepository,
                        DirectorRepository directorRepository,
                        MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    public MovieResponseDto save(MovieRequestDto dto) {
        Genre genre = genreRepository.findById(dto.getGenreId()).orElseThrow();
        Director director = directorRepository.findById(dto.getDirectorId()).orElseThrow();
        Movie movie = movieMapper.toEntity(dto);
        movie.setGenre(genre);
        movie.setDirector(director);

        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.toDto(savedMovie);
    }

    public List<MovieResponseDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movieMapper::toDto).toList();
    }

    public MovieResponseDto findById(Long id) {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return movieMapper.toDto(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
