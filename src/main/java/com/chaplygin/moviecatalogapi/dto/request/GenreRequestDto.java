package com.chaplygin.moviecatalogapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreRequestDto {

    @Schema(
            description = "Genre title",
            example = "Horror",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name of Genre must not be blank")
    @Size(max = 255, message = "Name of Genre length must not exceed 255 characters")
    private String name;
}
