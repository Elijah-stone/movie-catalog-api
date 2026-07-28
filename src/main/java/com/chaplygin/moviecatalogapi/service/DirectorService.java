package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.exception.DirectorNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.DirectorMapper;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    public Page<DirectorResponseDto> findAll(Pageable pageable) {
        return directorRepository.findAll(pageable).map(directorMapper::toDto);
    }

    public DirectorResponseDto findById(Long id) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
        return directorMapper.toDto(director);
    }

    public DirectorResponseDto save(DirectorRequestDto dto) {
        Director director = directorMapper.toEntity(dto);
        Director savedDirector = directorRepository.save(director);

        return directorMapper.toDto(savedDirector);
    }

    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));

        directorRepository.delete(director);
    }

    public DirectorResponseDto update(Long id, DirectorRequestDto dto) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
        director.setFirstName(dto.getFirstName());
        director.setLastName(dto.getLastName());
        Director savedDirector = directorRepository.save(director);
        return directorMapper.toDto(savedDirector);
    }

}
