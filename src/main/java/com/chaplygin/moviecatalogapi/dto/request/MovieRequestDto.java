package com.chaplygin.moviecatalogapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Size(min = 1888)
    private Integer releaseYear;

    private Integer duration;

    private Double rating;


    private Long genreId;
    private Long directorId;
}
