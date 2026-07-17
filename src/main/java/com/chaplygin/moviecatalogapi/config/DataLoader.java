package com.chaplygin.moviecatalogapi.config;

import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.entity.Movie;
import com.chaplygin.moviecatalogapi.service.DirectorService;
import com.chaplygin.moviecatalogapi.service.GenreService;
import com.chaplygin.moviecatalogapi.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class DataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final DirectorService directorService;
    private final GenreService genreService;

    public DataLoader(MovieService movieService,
                      DirectorService directorService,
                      GenreService genreService) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.genreService = genreService;
    }

    @Override
    public void run(String... args) {

        Genre genre = new Genre();
        genre.setName("Sci-fi");
        genre = genreService.save(genre);

        Director director = new Director();
        director.setFirstName("Christopher");
        director.setLastName("Nolan");
        director = directorService.save(director);

        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre(genre);
        movie.setDirector(director);
        movie = movieService.save(movie);



    }

}
