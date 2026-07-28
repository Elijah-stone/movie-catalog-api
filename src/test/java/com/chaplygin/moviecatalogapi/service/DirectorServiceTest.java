package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.exception.DirectorNotFoundException;
import com.chaplygin.moviecatalogapi.mapper.DirectorMapper;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
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
class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @Mock
    private DirectorMapper directorMapper;

    @InjectMocks
    private DirectorService directorService;

    @Test
    void findById_shouldReturnDirector_whenDirectorExists() {

        Director director = new Director();
        director.setId(1L);
        director.setFirstName("Christopher");
        director.setLastName("Nolan");

        DirectorResponseDto dto = new DirectorResponseDto();
        dto.setId(1L);
        dto.setFirstName("Christopher");
        dto.setLastName("Nolan");

        when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));

        when(directorMapper.toDto(director))
                .thenReturn(dto);

        DirectorResponseDto result = directorService.findById(1L);

        assertEquals("Christopher", result.getFirstName());
        assertEquals("Nolan", result.getLastName());

        verify(directorRepository).findById(1L);
    }

    @Test
    void findById_shouldThrowException_whenDirectorNotFound() {

        Long directorId = 1L;

        when(directorRepository.findById(directorId))
                .thenReturn(Optional.empty());

        assertThrows(
                DirectorNotFoundException.class,
                () -> directorService.findById(directorId)
        );

        verify(directorRepository).findById(directorId);
    }

    @Test
    void delete_shouldDeleteDirector_whenDirectorExists() {

        Director director = new Director();
        director.setId(1L);

        when(directorRepository.findById(1L))
                .thenReturn(Optional.of(director));

        directorService.delete(1L);

        verify(directorRepository).findById(1L);
        verify(directorRepository).delete(director);
    }

    @Test
    void delete_shouldThrowException_whenDirectorNotFound() {

        Long directorId = 1L;

        when(directorRepository.findById(directorId))
                .thenReturn(Optional.empty());

        assertThrows(
                DirectorNotFoundException.class,
                () -> directorService.delete(directorId)
        );

        verify(directorRepository).findById(directorId);
        verify(directorRepository, never()).delete(any(Director.class));
    }

    @Test
    void update_shouldUpdateDirector_whenDirectorExists() {

        Long directorId = 1L;

        DirectorRequestDto request = new DirectorRequestDto();
        request.setFirstName("Christopher");
        request.setLastName("Nolan");


        Director director = new Director();
        director.setId(directorId);
        director.setFirstName("Old");
        director.setLastName("Name");


        Director savedDirector = new Director();
        savedDirector.setId(directorId);
        savedDirector.setFirstName("Christopher");
        savedDirector.setLastName("Nolan");


        DirectorResponseDto response = new DirectorResponseDto();
        response.setId(directorId);
        response.setFirstName("Christopher");
        response.setLastName("Nolan");


        when(directorRepository.findById(directorId))
                .thenReturn(Optional.of(director));

        when(directorRepository.save(director))
                .thenReturn(savedDirector);

        when(directorMapper.toDto(savedDirector))
                .thenReturn(response);


        DirectorResponseDto result = directorService.update(directorId, request);


        assertEquals("Christopher", result.getFirstName());
        assertEquals("Nolan", result.getLastName());

        assertEquals("Christopher", director.getFirstName());
        assertEquals("Nolan", director.getLastName());


        verify(directorRepository).findById(directorId);
        verify(directorRepository).save(director);
        verify(directorMapper).toDto(savedDirector);
    }

    @Test
    void update_shouldThrowException_whenDirectorNotFound() {

        Long directorId = 1L;

        DirectorRequestDto request = new DirectorRequestDto();
        request.setFirstName("Christopher");
        request.setLastName("Nolan");


        when(directorRepository.findById(directorId))
                .thenReturn(Optional.empty());


        assertThrows(
                DirectorNotFoundException.class,
                () -> directorService.update(directorId, request)
        );


        verify(directorRepository).findById(directorId);

        verify(directorRepository, never())
                .save(any());

        verifyNoInteractions(directorMapper);
    }

}