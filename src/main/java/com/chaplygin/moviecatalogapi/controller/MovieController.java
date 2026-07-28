package com.chaplygin.moviecatalogapi.controller;

import com.chaplygin.moviecatalogapi.dto.request.MovieRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.MovieResponseDto;
import com.chaplygin.moviecatalogapi.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;




@RestController
@RequestMapping("/movies")
@Tag(
        name = "Movies",
        description = "Movie management endpoints"
)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
            summary = "Get all movies",
            description = "Returns paginated list of movies with sorting support"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Movies successfully retrieved"
            )
    })
    @GetMapping
    public Page<MovieResponseDto> findAll(
            @PageableDefault(
                    size = 10,
                    sort = "title"
            )
            Pageable pageable
    ) {
        return movieService.findAll(pageable);
    }



    @Operation(
            summary = "Filter movies",
            description = "Filters movies by title, minimum rating and release year with pagination and sorting"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Movies successfully filtered"
            )
    })
    @GetMapping("/filter")
    public Page<MovieResponseDto> filterByRating(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer minYear,
            @PageableDefault(
                    size = 10,
                    sort = "title"
            )
            Pageable pageable
    ) {
        return movieService.filter(title, minRating, minYear, pageable);
    }

    @Operation(
            summary = "Get movie by id",
            description = "Returns movie by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movie not found"
            )
    })
    @GetMapping("/{id:\\d+}")
    public MovieResponseDto findById(@PathVariable Long id) {
        return movieService.findById(id);
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
    public MovieResponseDto save(@Valid @RequestBody MovieRequestDto dto) {
        return movieService.save(dto);
    }


    @Operation(
            summary = "Delete movie",
            description = "Deletes movie by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Movie successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movie not found"
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }


    @Operation(
            summary = "Update movie",
            description = "Updates movie by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie successfully updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Movie not found"
            )
    })
    @PutMapping("/{id:\\d+}")
    public MovieResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequestDto dto
    ) {
        return movieService.update(id, dto);
    }


}
