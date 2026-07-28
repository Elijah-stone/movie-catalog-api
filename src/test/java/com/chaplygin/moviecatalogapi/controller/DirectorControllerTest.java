package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.exception.DirectorNotFoundException;
import com.chaplygin.moviecatalogapi.service.DirectorService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@WebMvcTest(DirectorController.class)
class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DirectorService directorService;

    @Test
    void findById_shouldReturnDirector_whenDirectorExists() throws Exception {

        DirectorResponseDto dto = new DirectorResponseDto();

        dto.setId(1L);
        dto.setFirstName("Christopher");
        dto.setLastName("Nolan");


        when(directorService.findById(1L))
                .thenReturn(dto);


        mockMvc.perform(
                        get("/directors/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Christopher"))
                .andExpect(jsonPath("$.lastName").value("Nolan"));
    }

    @Test
    void findById_shouldReturn404_whenDirectorNotFound() throws Exception {

        when(directorService.findById(1L))
                .thenThrow(new DirectorNotFoundException(1L));


        mockMvc.perform(
                        get("/directors/1")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_shouldReturnDirectorsPage() throws Exception {

        DirectorResponseDto dto = new DirectorResponseDto();

        dto.setId(1L);
        dto.setFirstName("Christopher");
        dto.setLastName("Nolan");


        Page<DirectorResponseDto> page = new PageImpl<>(
                List.of(dto)
        );


        when(directorService.findAll(any(Pageable.class)))
                .thenReturn(page);


        mockMvc.perform(
                        get("/directors")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].firstName").value("Christopher"))
                .andExpect(jsonPath("$.content[0].lastName").value("Nolan"));
    }

    @Test
    void save_shouldCreateDirector() throws Exception {

        DirectorRequestDto request = new DirectorRequestDto();

        request.setFirstName("Christopher");
        request.setLastName("Nolan");


        DirectorResponseDto response = new DirectorResponseDto();

        response.setId(1L);
        response.setFirstName("Christopher");
        response.setLastName("Nolan");


        when(directorService.save(any(DirectorRequestDto.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/directors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Christopher"))
                .andExpect(jsonPath("$.lastName").value("Nolan"));


        verify(directorService)
                .save(any(DirectorRequestDto.class));
    }

    @Test
    void delete_shouldDeleteDirector() throws Exception {

        mockMvc.perform(
                        delete("/directors/1")
                )
                .andExpect(status().isNoContent());


        verify(directorService)
                .delete(1L);
    }

    @Test
    void update_shouldUpdateDirector() throws Exception {

        DirectorRequestDto request = new DirectorRequestDto();

        request.setFirstName("Christopher");
        request.setLastName("Nolan");


        DirectorResponseDto response = new DirectorResponseDto();

        response.setId(1L);
        response.setFirstName("Christopher");
        response.setLastName("Nolan");


        when(directorService.update(
                eq(1L),
                any(DirectorRequestDto.class)
        ))
                .thenReturn(response);


        mockMvc.perform(
                        put("/directors/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Christopher"))
                .andExpect(jsonPath("$.lastName").value("Nolan"));


        verify(directorService)
                .update(
                        eq(1L),
                        any(DirectorRequestDto.class)
                );
    }

}
