package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.entity.Genre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreMapperTest {


    private final GenreMapper genreMapper = new GenreMapper();


    @Test
    void toDto_shouldMapGenreToResponseDto() {

        Genre genre = new Genre();

        genre.setId(1L);
        genre.setName("Sci-fi");


        GenreResponseDto result = genreMapper.toDto(genre);


        assertEquals(1L, result.getId());
        assertEquals("Sci-fi", result.getName());
    }


    @Test
    void toEntity_shouldMapRequestDtoToGenre() {

        GenreRequestDto dto = new GenreRequestDto();

        dto.setName("Sci-fi");


        Genre result = genreMapper.toEntity(dto);


        assertEquals("Sci-fi", result.getName());
    }
}
