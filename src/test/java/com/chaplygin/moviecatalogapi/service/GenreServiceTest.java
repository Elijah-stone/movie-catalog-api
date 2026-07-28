package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.exception.GenreNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.GenreMapper;
import com.chaplygin.moviecatalogapi.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMapper genreMapper;

    @InjectMocks
    private GenreService genreService;

    @Test
    void findById_shouldReturnGenre_whenGenreExists() {

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sci-fi");

        GenreResponseDto dto = new GenreResponseDto();
        dto.setId(1L);
        dto.setName("Sci-fi");

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        when(genreMapper.toDto(genre))
                .thenReturn(dto);

        GenreResponseDto result = genreService.findById(1L);

        assertEquals("Sci-fi", result.getName());

        verify(genreRepository).findById(1L);
    }

    @Test
    void findById_shouldThrowException_whenGenreNotFound() {

        Long genreId = 1L;

        when(genreRepository.findById(genreId))
                .thenReturn(Optional.empty());

        assertThrows(
                GenreNotFoundException.class,
                () -> genreService.findById(genreId)
        );

        verify(genreRepository).findById(genreId);
    }

    @Test
    void delete_shouldDeleteGenre_whenGenreExists() {

        Genre genre = new Genre();
        genre.setId(1L);

        when(genreRepository.findById(1L))
                .thenReturn(Optional.of(genre));

        genreService.delete(1L);

        verify(genreRepository).findById(1L);
        verify(genreRepository).delete(genre);
    }

    @Test
    void delete_shouldThrowException_whenGenreNotFound() {

        Long genreId = 1L;

        when(genreRepository.findById(genreId))
                .thenReturn(Optional.empty());

        assertThrows(
                GenreNotFoundException.class,
                () -> genreService.delete(genreId)
        );

        verify(genreRepository).findById(genreId);
        verify(genreRepository, never()).delete(any(Genre.class));
    }

    @Test
    void update_shouldUpdateGenre_whenGenreExists() {

        Long genreId = 1L;

        GenreRequestDto request = new GenreRequestDto();
        request.setName("Fantasy");


        Genre genre = new Genre();
        genre.setId(genreId);
        genre.setName("Old name");


        Genre savedGenre = new Genre();
        savedGenre.setId(genreId);
        savedGenre.setName("Fantasy");


        GenreResponseDto response = new GenreResponseDto();
        response.setId(genreId);
        response.setName("Fantasy");


        when(genreRepository.findById(genreId))
                .thenReturn(Optional.of(genre));

        when(genreRepository.save(genre))
                .thenReturn(savedGenre);

        when(genreMapper.toDto(savedGenre))
                .thenReturn(response);


        GenreResponseDto result = genreService.update(genreId, request);


        assertEquals("Fantasy", result.getName());

        assertEquals("Fantasy", genre.getName());


        verify(genreRepository).findById(genreId);
        verify(genreRepository).save(genre);
        verify(genreMapper).toDto(savedGenre);
    }

    @Test
    void update_shouldThrowException_whenGenreNotFound() {

        Long genreId = 1L;

        GenreRequestDto request = new GenreRequestDto();
        request.setName("Fantasy");


        when(genreRepository.findById(genreId))
                .thenReturn(Optional.empty());


        assertThrows(
                GenreNotFoundException.class,
                () -> genreService.update(genreId, request)
        );


        verify(genreRepository).findById(genreId);

        verify(genreRepository, never())
                .save(any());

        verifyNoInteractions(genreMapper);
    }

}