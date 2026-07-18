package com.chaplygin.moviecatalogapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponseDto {

    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Double rating;
    private Integer duration;

    private GenreResponseDto genre;
    private DirectorResponseDto director;
}
