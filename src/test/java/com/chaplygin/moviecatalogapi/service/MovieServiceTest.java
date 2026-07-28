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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findById_shouldReturnMovie_whenMovieExists() {

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");

        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(1L);
        dto.setTitle("Inception");

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(movie));

        when(movieMapper.toDto(movie))
                .thenReturn(dto);

        MovieResponseDto result = movieService.findById(1L);


        assertEquals("Inception", result.getTitle());

        verify(movieRepository).findById(1L);
    }

    @Test
    void findById_shouldThrowException_whenMovieNotFound() {

        Long movieId = 1L;

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.empty());

        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.findById(movieId)
        );

        verify(movieRepository).findById(movieId);
    }

    @Test
    void delete_shouldDeleteMovie_whenMovieExists() {
        Movie movie = new Movie();
        movie.setId(1L);

        when(movieRepository.findById(1L))
                .thenReturn(Optional.of(movie));

        movieService.delete(1L);

        verify(movieRepository).findById(1L);
        verify(movieRepository).delete(movie);
    }

    @Test
    void delete_shouldThrowException_whenMovieNotFound() {
        Long movieId = 1L;

        when(movieRepository.findById(movieId))
                .thenReturn(Optional.empty());

        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.delete(movieId)
        );

        verify(movieRepository).findById(movieId);
        verify(movieRepository, never()).delete(any(Movie.class));
    }

    @Test
    void save_shouldReturnMovie_whenGenreAndDirectorExist() {

        MovieRequestDto request = new MovieRequestDto();

        request.setTitle("Inception");
        request.setGenreId(1L);
        request.setDirectorId(1L);


        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sci-fi");


        Director director = new Director();
        director.setId(1L);
        director.setFirstName("Christopher");
        director.setLastName("Nolan");


        Movie movie = new Movie();
        movie.setTitle("Inception");


        Movie savedMovie = new Movie();
        savedMovie.setId(1L);
        savedMovie.setTitle("Inception");


        MovieResponseDto response = new MovieResponseDto();
        response.setId(1L);
        response.setTitle("Inception");


        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));

        when(movieMapper.toEntity(request))
                .thenReturn(movie);

        when(movieRepository.save(movie))
                .thenReturn(savedMovie);

        when(movieMapper.toDto(savedMovie))
                .thenReturn(response);


        MovieResponseDto result = movieService.save(request);


        assertEquals("Inception", result.getTitle());


        verify(genreRepository).findById(1L);
        verify(directorRepository).findById(1L);
        verify(movieMapper).toEntity(request);
        verify(movieRepository).save(movie);
        verify(movieMapper).toDto(savedMovie);
    }


    @Test
    void save_shouldThrowException_whenGenreNotFound() {

        MovieRequestDto request = new MovieRequestDto();

        request.setTitle("Inception");
        request.setGenreId(1L);
        request.setDirectorId(1L);


        when(genreRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                GenreNotFoundException.class,
                () -> movieService.save(request)
        );


        verify(genreRepository).findById(1L);

        verifyNoInteractions(directorRepository);
        verifyNoInteractions(movieMapper);
        verifyNoInteractions(movieRepository);
    }

    @Test
    void save_shouldThrowException_whenDirectorNotFound() {

        MovieRequestDto request = new MovieRequestDto();

        request.setTitle("Inception");
        request.setGenreId(1L);
        request.setDirectorId(1L);


        Genre genre = new Genre();
        genre.setId(1L);


        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));


        when(directorRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                DirectorNotFoundException.class,
                () -> movieService.save(request)
        );


        verify(genreRepository).findById(1L);
        verify(directorRepository).findById(1L);

        verifyNoInteractions(movieMapper);
        verifyNoInteractions(movieRepository);
    }

    @Test
    void update_shouldUpdateMovie_whenDataValid() {

        Long movieId = 1L;

        MovieRequestDto request = new MovieRequestDto();
        request.setTitle("Interstellar");
        request.setDescription("Space movie");
        request.setReleaseYear(2014);
        request.setDuration(169);
        request.setRating(8.7);
        request.setGenreId(1L);
        request.setDirectorId(1L);


        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Inception");


        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sci-fi");


        Director director = new Director();
        director.setId(1L);
        director.setFirstName("Christopher");
        director.setLastName("Nolan");


        Movie savedMovie = new Movie();
        savedMovie.setId(movieId);
        savedMovie.setTitle("Interstellar");


        MovieResponseDto response = new MovieResponseDto();
        response.setId(movieId);
        response.setTitle("Interstellar");


        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));

        when(movieRepository.save(movie))
                .thenReturn(savedMovie);

        when(movieMapper.toDto(savedMovie))
                .thenReturn(response);


        MovieResponseDto result = movieService.update(movieId, request);


        assertEquals("Interstellar", result.getTitle());

        assertEquals("Interstellar", movie.getTitle());
        assertEquals("Space movie", movie.getDescription());
        assertEquals(2014, movie.getReleaseYear());
        assertEquals(169, movie.getDuration());
        assertEquals(8.7, movie.getRating());

        assertEquals(genre, movie.getGenre());
        assertEquals(director, movie.getDirector());


        verify(movieRepository).findById(movieId);
        verify(genreRepository).findById(1L);
        verify(directorRepository).findById(1L);
        verify(movieRepository).save(movie);
        verify(movieMapper).toDto(savedMovie);
    }

    @Test
    void update_shouldThrowException_whenMovieNotFound() {

        Long movieId = 1L;

        MovieRequestDto request = new MovieRequestDto();


        when(movieRepository.findById(movieId))
                .thenReturn(Optional.empty());


        assertThrows(
                MovieNotFoundException.class,
                () -> movieService.update(movieId, request)
        );


        verify(movieRepository).findById(movieId);

        verifyNoInteractions(
                genreRepository,
                directorRepository,
                movieMapper
        );

        verify(movieRepository, never())
                .save(any());
    }

    @Test
    void update_shouldThrowException_whenGenreNotFound() {

        Long movieId = 1L;

        MovieRequestDto request = new MovieRequestDto();
        request.setGenreId(1L);
        request.setDirectorId(1L);


        Movie movie = new Movie();
        movie.setId(movieId);


        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));


        when(genreRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                GenreNotFoundException.class,
                () -> movieService.update(movieId, request)
        );


        verify(movieRepository).findById(movieId);
        verify(genreRepository).findById(1L);

        verifyNoInteractions(directorRepository);

        verify(movieRepository, never())
                .save(any());
    }

    @Test
    void update_shouldThrowException_whenDirectorNotFound() {

        Long movieId = 1L;

        MovieRequestDto request = new MovieRequestDto();
        request.setGenreId(1L);
        request.setDirectorId(1L);


        Movie movie = new Movie();
        movie.setId(movieId);


        Genre genre = new Genre();
        genre.setId(1L);


        when(movieRepository.findById(movieId))
                .thenReturn(Optional.of(movie));


        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));


        when(directorRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                DirectorNotFoundException.class,
                () -> movieService.update(movieId, request)
        );


        verify(movieRepository).findById(movieId);
        verify(genreRepository).findById(1L);
        verify(directorRepository).findById(1L);

        verify(movieRepository, never())
                .save(any());
    }

}