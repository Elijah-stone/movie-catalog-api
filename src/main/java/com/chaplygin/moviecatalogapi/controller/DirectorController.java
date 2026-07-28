package com.chaplygin.moviecatalogapi.controller;


import com.chaplygin.moviecatalogapi.dto.request.DirectorRequestDto;
import com.chaplygin.moviecatalogapi.dto.response.DirectorResponseDto;
import com.chaplygin.moviecatalogapi.service.DirectorService;
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
@RequestMapping("/directors")
@Tag(
        name = "Directors",
        description = "Director management endpoints"
)
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }


    @Operation(
            summary = "Get all directors",
            description = "Returns paginated list of directors with sorting support"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Directors successfully retrieved"
            )
    })
    @GetMapping
    public Page<DirectorResponseDto> findAll(
            @PageableDefault(
                    size = 10,
                    sort = "lastName"
            )
            Pageable pageable
    ) {
        return directorService.findAll(pageable);
    }

    @Operation(
            summary = "Get director by id",
            description = "Returns director by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director not found"
            )
    })
    @GetMapping("/{id:\\d+}")
    public DirectorResponseDto findById(@PathVariable Long id) {
        return directorService.findById(id);
    }

    @Operation(
            summary = "Create new director",
            description = "Creates a new director with validation"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Director successfully created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error"
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DirectorResponseDto save(@Valid @RequestBody DirectorRequestDto dto) {
        return directorService.save(dto);
    }

    @Operation(
            summary = "Delete genre",
            description = "Deletes director by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Director successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director not found"
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }


    @Operation(
            summary = "Update director",
            description = "Updates director by unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director successfully updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director not found"
            )
    })
    @PutMapping("/{id:\\d+}")
    public DirectorResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody DirectorRequestDto dto
    ) {
        return directorService.update(id, dto);
    }

}
