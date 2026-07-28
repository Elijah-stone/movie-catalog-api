package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.request.GenreRequestDto;
import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.GenreResponseDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@Tag(
        name = "Genres",
        description = "Genre management endpoints"
)
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(
            summary = "Get all genres",
            description = "Returns paginated list of genres with sorting support"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Genres successfully retrieved"
            )
    })
    @GetMapping
    public Page<GenreResponseDto> findAll(
            @PageableDefault(
                    size = 10,
                    sort = "name"
            )
            Pageable pageable
    ) {
        return genreService.findAll(pageable);
    }

    @Operation(
            summary = "Get genre by id",
            description = "Returns genre by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Genre found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Genre not found"
            )
    })
    @GetMapping("/{id:\\d+}")
    public GenreResponseDto findById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @Operation(
            summary = "Create new movie",
            description = "Creates a new movie with validation"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Movie successfully created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error"
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GenreResponseDto save(@Valid @RequestBody GenreRequestDto dto) {
        return genreService.save(dto);
    }

    @Operation(
            summary = "Delete genre",
            description = "Deletes genre by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Genre successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Genre not found"
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }


    @Operation(
            summary = "Update genre",
            description = "Updates genre by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Genre successfully updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Genre not found"
            )
    })
    @PutMapping("/{id:\\d+}")
    public GenreResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody GenreRequestDto dto
    ) {
        return genreService.update(id, dto);
    }

}
