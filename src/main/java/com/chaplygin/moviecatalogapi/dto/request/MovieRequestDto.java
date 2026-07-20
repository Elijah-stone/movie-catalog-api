package com.chaplygin.moviecatalogapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title length must not exceed 255 characters")
    private String title;

    @Size(max = 1000, message = "Description length must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be at least 1888")
    private Integer releaseYear;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10")
    @Digits(integer = 2, fraction = 1, message = "Rating must have maximum one decimal place")
    private Double rating;

    @NotNull(message = "Genre id is required")
    @Positive(message = "Genre id must be positive")
    private Long genreId;
    @NotNull(message = "Director id is required")
    @Positive(message = "Director id must be positive")
    private Long directorId;
}
