package com.chaplygin.moviecatalogapi.dto.request;

import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    @Schema(
            description = "Movie title",
            example = "Interstellar",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title length must not exceed 255 characters")
    private String title;

    @Schema(
            description = "Movie description",
            example = "A team travels through a wormhole searching for a new home for humanity"
    )
    @NotBlank(message = "Description must not be blank")
    @Size(max = 1000, message = "Description length must not exceed 1000 characters")
    private String description;

    @Schema(
            description = "Movie release year",
            example = "2014",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be at least 1888")
    private Integer releaseYear;

    @Schema(
            description = "Movie duration in minutes",
            example = "169",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    @Schema(
            description = "Movie rating from 0 to 10",
            example = "8.7",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must not exceed 10")
    @Digits(integer = 2, fraction = 1, message = "Rating must have maximum one decimal place")
    private Double rating;

    @Schema(
            description = "Genre identifier",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Genre id is required")
    @Positive(message = "Genre id must be positive")
    private Long genreId;

    @Schema(
            description = "Director identifier",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Director id is required")
    @Positive(message = "Director id must be positive")
    private Long directorId;
}
