package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.entity.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieMapperTest {


    private final GenreMapper genreMapper = new GenreMapper();
    private final DirectorMapper directorMapper = new DirectorMapper();

    private final MovieMapper movieMapper =
            new MovieMapper(genreMapper, directorMapper);



    @Test
    void toDto_shouldMapMovieToResponseDto() {

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sci-fi");


        Director director = new Director();
        director.setId(1L);
        director.setFirstName("Christopher");
        director.setLastName("Nolan");


        Movie movie = new Movie();

        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setDescription("Dream movie");
        movie.setReleaseYear(2010);
        movie.setDuration(148);
        movie.setRating(8.8);
        movie.setGenre(genre);
        movie.setDirector(director);



        MovieResponseDto result = movieMapper.toDto(movie);



        assertEquals(1L, result.getId());
        assertEquals("Inception", result.getTitle());
        assertEquals("Dream movie", result.getDescription());
        assertEquals(2010, result.getReleaseYear());
        assertEquals(148, result.getDuration());
        assertEquals(8.8, result.getRating());

        assertEquals("Sci-fi", result.getGenre().getName());
        assertEquals("Christopher", result.getDirector().getFirstName());
    }



    @Test
    void toEntity_shouldMapRequestDtoToMovie() {

        MovieRequestDto dto = new MovieRequestDto();

        dto.setTitle("Inception");
        dto.setDescription("Dream movie");
        dto.setReleaseYear(2010);
        dto.setDuration(148);
        dto.setRating(8.8);



        Movie result = movieMapper.toEntity(dto);



        assertEquals("Inception", result.getTitle());
        assertEquals("Dream movie", result.getDescription());
        assertEquals(2010, result.getReleaseYear());
        assertEquals(148, result.getDuration());
        assertEquals(8.8, result.getRating());
    }
}