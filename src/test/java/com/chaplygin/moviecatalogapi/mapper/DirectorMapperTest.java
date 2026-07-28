package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectorMapperTest {


    private final DirectorMapper directorMapper = new DirectorMapper();


    @Test
    void toDto_shouldMapDirectorToResponseDto() {

        Director director = new Director();

        director.setId(1L);
        director.setFirstName("Christopher");
        director.setLastName("Nolan");


        DirectorResponseDto result = directorMapper.toDto(director);


        assertEquals(1L, result.getId());
        assertEquals("Christopher", result.getFirstName());
        assertEquals("Nolan", result.getLastName());
    }


    @Test
    void toEntity_shouldMapRequestDtoToDirector() {

        DirectorRequestDto dto = new DirectorRequestDto();

        dto.setFirstName("Christopher");
        dto.setLastName("Nolan");


        Director result = directorMapper.toEntity(dto);


        assertEquals("Christopher", result.getFirstName());
        assertEquals("Nolan", result.getLastName());
    }
}