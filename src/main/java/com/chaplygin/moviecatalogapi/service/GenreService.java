package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.entity.Genre;
import com.chaplygin.moviecatalogapi.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

}
