package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.exception.MovieNotFoundException;
import com.chaplygin.moviecatalogapi.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MovieService movieService;

    @Test
    void findById_shouldReturnMovie_whenMovieExists() throws Exception {

        MovieResponseDto dto = new MovieResponseDto();

        dto.setId(1L);
        dto.setTitle("Inception");


        when(movieService.findById(1L))
                .thenReturn(dto);


        mockMvc.perform(
                        get("/movies/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void findById_shouldReturn404_whenMovieNotFound() throws Exception {

        when(movieService.findById(1L))
                .thenThrow(new MovieNotFoundException(1L));


        mockMvc.perform(
                        get("/movies/1")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_shouldReturnMoviesPage() throws Exception {

        MovieResponseDto dto = new MovieResponseDto();

        dto.setId(1L);
        dto.setTitle("Inception");


        Page<MovieResponseDto> page = new PageImpl<>(
                List.of(dto)
        );


        when(movieService.findAll(any(Pageable.class)))
                .thenReturn(page);


        mockMvc.perform(
                        get("/movies")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Inception"));
    }

    @Test
    void save_shouldCreateMovie() throws Exception {

        MovieRequestDto request = new MovieRequestDto();

        request.setTitle("Inception");
        request.setDescription("Dream movie");
        request.setReleaseYear(2010);
        request.setDuration(148);
        request.setRating(8.8);
        request.setGenreId(1L);
        request.setDirectorId(1L);


        MovieResponseDto response = new MovieResponseDto();

        response.setId(1L);
        response.setTitle("Inception");


        when(movieService.save(any(MovieRequestDto.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/movies")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Inception"));


        verify(movieService)
                .save(any(MovieRequestDto.class));
    }

    @Test
    void update_shouldUpdateMovie() throws Exception {

        MovieRequestDto request = new MovieRequestDto();

        request.setTitle("Inception Updated");
        request.setDescription("Updated description");
        request.setReleaseYear(2010);
        request.setDuration(150);
        request.setRating(9.0);
        request.setGenreId(1L);
        request.setDirectorId(1L);


        MovieResponseDto response = new MovieResponseDto();

        response.setId(1L);
        response.setTitle("Inception Updated");


        when(movieService.update(
                any(Long.class),
                any(MovieRequestDto.class)
        ))
                .thenReturn(response);


        mockMvc.perform(
                        put("/movies/1")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Inception Updated"));


        verify(movieService)
                .update(any(Long.class), any(MovieRequestDto.class));
    }

    @Test
    void delete_shouldDeleteMovie() throws Exception {


        mockMvc.perform(
                        delete("/movies/1")
                )
                .andExpect(status().isNoContent());


        verify(movieService)
                .delete(1L);
    }

    @Test
    void filter_shouldReturnFilteredMovies() throws Exception {


        MovieResponseDto dto = new MovieResponseDto();

        dto.setId(1L);
        dto.setTitle("Inception");


        Page<MovieResponseDto> page = new PageImpl<>(
                List.of(dto)
        );


        when(movieService.filter(
                any(),
                any(),
                any(),
                any(Pageable.class)
        ))
                .thenReturn(page);



        mockMvc.perform(
                        get("/movies/filter")
                                .param("title", "Inception")
                                .param("minRating", "8.0")
                                .param("minYear", "2000")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title")
                        .value("Inception"));
    }

}