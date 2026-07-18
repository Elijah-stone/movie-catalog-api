package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.mapper.DirectorMapper;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    public DirectorResponseDto save(DirectorRequestDto dto) {
        Director director = directorMapper.toEntity(dto);
        Director savedDirector = directorRepository.save(director);

        return directorMapper.toDto(savedDirector);
    }

}
