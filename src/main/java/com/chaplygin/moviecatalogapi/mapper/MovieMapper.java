package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final GenreMapper genreMapper;
    private final DirectorMapper directorMapper;

    public MovieMapper(GenreMapper genreMapper, DirectorMapper directorMapper) {
        this.genreMapper = genreMapper;
        this.directorMapper = directorMapper;
    }

    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDuration(movie.getDuration());
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setRating(movie.getRating());
        movieResponseDto.setReleaseYear(movie.getReleaseYear());

        movieResponseDto.setGenre(genreMapper.toDto(movie.getGenre()));
        movieResponseDto.setDirector(directorMapper.toDto(movie.getDirector()));

        return movieResponseDto;
    }

    public Movie toEntity(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDuration(movieRequestDto.getDuration());
        movie.setDescription(movieRequestDto.getDescription());
        movie.setRating(movieRequestDto.getRating());
        movie.setReleaseYear(movieRequestDto.getReleaseYear());

        return movie;
    }
}
