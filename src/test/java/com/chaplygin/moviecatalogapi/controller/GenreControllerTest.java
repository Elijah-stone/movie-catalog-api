package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.exception.GenreNotFoundException;
import com.chaplygin.moviecatalogapi.service.GenreService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private GenreService genreService;


    @Test
    void findById_shouldReturnGenre_whenGenreExists() throws Exception {

        GenreResponseDto dto = new GenreResponseDto();

        dto.setId(1L);
        dto.setName("Sci-fi");


        when(genreService.findById(1L))
                .thenReturn(dto);


        mockMvc.perform(
                        get("/genres/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sci-fi"));
    }

    @Test
    void findById_shouldReturn404_whenGenreNotFound() throws Exception {

        when(genreService.findById(1L))
                .thenThrow(new GenreNotFoundException(1L));


        mockMvc.perform(
                        get("/genres/1")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_shouldReturnGenresPage() throws Exception {

        GenreResponseDto dto = new GenreResponseDto();

        dto.setId(1L);
        dto.setName("Sci-fi");


        Page<GenreResponseDto> page = new PageImpl<>(
                List.of(dto)
        );


        when(genreService.findAll(any(Pageable.class)))
                .thenReturn(page);


        mockMvc.perform(
                        get("/genres")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Sci-fi"));
    }

    @Test
    void save_shouldCreateGenre() throws Exception {

        GenreRequestDto request = new GenreRequestDto();

        request.setName("Sci-fi");


        GenreResponseDto response = new GenreResponseDto();

        response.setId(1L);
        response.setName("Sci-fi");


        when(genreService.save(any(GenreRequestDto.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/genres")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sci-fi"));


        verify(genreService)
                .save(any(GenreRequestDto.class));
    }

    @Test
    void delete_shouldDeleteGenre() throws Exception {

        mockMvc.perform(
                        delete("/genres/1")
                )
                .andExpect(status().isNoContent());


        verify(genreService)
                .delete(1L);
    }

    @Test
    void update_shouldUpdateGenre() throws Exception {

        GenreRequestDto request = new GenreRequestDto();

        request.setName("Science Fiction");


        GenreResponseDto response = new GenreResponseDto();

        response.setId(1L);
        response.setName("Science Fiction");


        when(genreService.update(
                any(Long.class),
                any(GenreRequestDto.class)
        ))
                .thenReturn(response);


        mockMvc.perform(
                        put("/genres/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Science Fiction"));


        verify(genreService)
                .update(
                        any(Long.class),
                        any(GenreRequestDto.class)
                );
    }

}
