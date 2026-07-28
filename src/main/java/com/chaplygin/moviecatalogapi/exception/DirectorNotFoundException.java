package com.chaplygin.moviecatalogapi.exception;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(Long id) {
        super("Director with id " + id + " not found");
    }
}
