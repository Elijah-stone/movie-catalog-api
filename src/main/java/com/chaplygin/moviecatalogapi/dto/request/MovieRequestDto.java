package com.chaplygin.moviecatalogapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    private String title;
    private String description;
    private Integer releaseYear;
    private Integer duration;
    private Double rating;

    private Long genreId;
    private Long directorId;
}
