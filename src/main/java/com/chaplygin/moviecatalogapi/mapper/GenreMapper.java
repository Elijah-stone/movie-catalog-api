package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponseDto toDto(Genre genre) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();
        genreResponseDto.setId(genre.getId());
        genreResponseDto.setName(genre.getName());

        return genreResponseDto;
    }

    public Genre toEntity(GenreRequestDto dto){
        Genre genre = new Genre();
        genre.setName(dto.getName());

        return genre;
    }
}
