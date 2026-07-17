package com.chaplygin.moviecatalogapi.service;

import com.chaplygin.moviecatalogapi.entity.Director;
import com.chaplygin.moviecatalogapi.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

}
