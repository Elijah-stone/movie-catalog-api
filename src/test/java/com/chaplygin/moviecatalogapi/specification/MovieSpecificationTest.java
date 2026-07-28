package com.chaplygin.moviecatalogapi.specification;

import com.chaplygin.moviecatalogapi.entity.Movie;
import com.chaplygin.moviecatalogapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MovieSpecificationTest {


    @Autowired
    private MovieRepository movieRepository;


    @Test
    void titleContains_shouldFindMovieByTitle() {

        Movie movie = new Movie();

        movie.setTitle("Inception");
        movie.setRating(8.8);
        movie.setReleaseYear(2010);


        movieRepository.save(movie);


        List<Movie> result = movieRepository.findAll(
                MovieSpecification.titleContains("ince")
        );


        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
    }



    @Test
    void ratingGreaterThanOrEqual_shouldFindMoviesWithHigherRating() {

        Movie movie1 = new Movie();

        movie1.setTitle("Inception");
        movie1.setRating(8.8);
        movie1.setReleaseYear(2010);


        Movie movie2 = new Movie();

        movie2.setTitle("Bad Movie");
        movie2.setRating(5.0);
        movie2.setReleaseYear(2010);


        movieRepository.saveAll(
                List.of(movie1, movie2)
        );


        List<Movie> result = movieRepository.findAll(
                MovieSpecification.ratingGreaterThanOrEqual(8.0)
        );


        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
    }



    @Test
    void releaseYearGreaterThanOrEqual_shouldFindMoviesAfterYear() {

        Movie movie1 = new Movie();

        movie1.setTitle("Old Movie");
        movie1.setReleaseYear(1990);


        Movie movie2 = new Movie();

        movie2.setTitle("New Movie");
        movie2.setReleaseYear(2020);


        movieRepository.saveAll(
                List.of(movie1, movie2)
        );


        List<Movie> result = movieRepository.findAll(
                MovieSpecification.releaseYearGreaterThanOrEqual(2000)
        );


        assertEquals(1, result.size());
        assertEquals("New Movie", result.get(0).getTitle());
    }
}
