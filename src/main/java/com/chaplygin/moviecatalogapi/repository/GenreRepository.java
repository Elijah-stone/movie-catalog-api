package com.chaplygin.moviecatalogapi.repository;

import com.chaplygin.moviecatalogapi.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
