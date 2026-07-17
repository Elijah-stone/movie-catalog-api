package com.chaplygin.moviecatalogapi.repository;

import com.chaplygin.moviecatalogapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
