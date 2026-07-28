package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.exception.GenreNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.GenreMapper;
import com.chaplygin.moviecatalogapi.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public Page<GenreResponseDto> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable).map(genreMapper::toDto);
    }

    public GenreResponseDto findById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        return genreMapper.toDto(genre);
    }

    public GenreResponseDto save(GenreRequestDto dto) {
        Genre genre = genreMapper.toEntity(dto);
        Genre savedGenre = genreRepository.save(genre);

        return genreMapper.toDto(savedGenre);

    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    public GenreResponseDto update(Long id, GenreRequestDto dto) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        genre.setName(dto.getName());
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toDto(savedGenre);
    }

}
