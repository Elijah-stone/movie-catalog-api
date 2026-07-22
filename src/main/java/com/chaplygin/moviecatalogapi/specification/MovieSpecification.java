package com.chaplygin.moviecatalogapi.specification;

import com.chaplygin.moviecatalogapi.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> titleContains(String title) {

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );


    }

    public static Specification<Movie> ratingGreaterThanOrEqual(Double rating) {

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("rating"),
                        rating
                ));

    }

    public static Specification<Movie> releaseYearGreaterThanOrEqual(Integer year) {

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("releaseYear"),
                        year
                        )
        );

    }

}
