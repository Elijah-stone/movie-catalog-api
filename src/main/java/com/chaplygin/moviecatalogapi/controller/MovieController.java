package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponseDto> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MovieResponseDto> findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieResponseDto save(@RequestBody MovieRequestDto dto) {
        return movieService.save(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

}
