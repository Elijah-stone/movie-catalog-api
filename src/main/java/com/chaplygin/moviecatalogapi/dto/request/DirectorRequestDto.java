package com.chaplygin.moviecatalogapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorRequestDto {
    @Schema(
            description = "Director first Name",
            example = "Martin",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Director first Name must not be blank")
    @Size(max = 255, message = "Director first Name length must not exceed 255 characters")
    private String firstName;

    @Schema(
            description = "Director last Name",
            example = "Scorsese",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Director last Name must not be blank")
    @Size(max = 255, message = "Director last Name length must not exceed 255 characters")
    private String lastName;
}
