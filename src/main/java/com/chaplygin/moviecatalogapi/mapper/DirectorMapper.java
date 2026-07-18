package com.chaplygin.moviecatalogapi.mapper;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public DirectorResponseDto toDto(Director director) {
        DirectorResponseDto directorResponseDto = new DirectorResponseDto();
        directorResponseDto.setId(director.getId());
        directorResponseDto.setFirstName(director.getFirstName());
        directorResponseDto.setLastName(director.getLastName());

        return directorResponseDto;
    }

    public Director toEntity(DirectorRequestDto dto) {
        Director director = new Director();
        director.setFirstName(dto.getFirstName());
        director.setLastName(dto.getLastName());

        return director;
    }


}
