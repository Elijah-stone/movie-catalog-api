package com.chaplygin.moviecatalogapi.repository;

import com.chaplygin.moviecatalogapi.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
