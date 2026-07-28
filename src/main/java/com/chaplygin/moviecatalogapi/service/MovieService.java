package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.entity.Movie;
import com.chaplygin.moviecatalogapi.exception.DirectorNotFoundException;
import com.chaplygin.moviecatalogapi.exception.GenreNotFoundException;
import com.chaplygin.moviecatalogapi.exception.MovieNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.MovieMapper;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
import com.chaplygin.moviecatalogapi.repository.GenreRepository;
import com.chaplygin.moviecatalogapi.repository.MovieRepository;
import com.chaplygin.moviecatalogapi.specification.MovieSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;





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
        Genre genre = genreRepository.findById(dto.getGenreId()).orElseThrow(() -> new GenreNotFoundException(dto.getGenreId()));
        Director director = directorRepository.findById(dto.getDirectorId()).orElseThrow(() -> new DirectorNotFoundException((dto.getDirectorId())));
        Movie movie = movieMapper.toEntity(dto);
        movie.setGenre(genre);
        movie.setDirector(director);

        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.toDto(savedMovie);
    }

    public Page<MovieResponseDto> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieMapper::toDto);
    }

    public MovieResponseDto findById(Long id) {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        return movieMapper.toDto(movie);
    }

    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movieRepository.delete(movie);
    }

    public MovieResponseDto update(Long id, MovieRequestDto dto) {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        Genre genre = genreRepository.findById(dto.getGenreId()).orElseThrow(() -> new GenreNotFoundException(dto.getGenreId()));
        Director director = directorRepository.findById((dto.getDirectorId())).orElseThrow(() -> new DirectorNotFoundException(dto.getDirectorId()));

        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setDuration(dto.getDuration());
        movie.setRating(dto.getRating());
        movie.setGenre(genre);
        movie.setDirector(director);

        Movie savedMovie = movieRepository.save(movie);

        return  movieMapper.toDto(savedMovie);
    }



    public Page<MovieResponseDto> filter(String title,
                                         Double minRating,
                                         Integer minYear,
                                         Pageable pageable
    ) {

        Specification<Movie> specification = (root, query, cb) -> null;

        if (title != null) {
            specification = specification.and(MovieSpecification.titleContains(title));
        }

        if (minRating != null) {
            specification = specification.and(
                    MovieSpecification.ratingGreaterThanOrEqual(minRating)
            );
        }

        if (minYear != null) {
            specification = specification.and(
                    MovieSpecification.releaseYearGreaterThanOrEqual(minYear)
            );
        }

        return movieRepository.findAll(specification, pageable)
                .map(movieMapper::toDto);
    }



}
