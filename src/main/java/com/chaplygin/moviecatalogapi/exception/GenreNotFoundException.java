package com.chaplygin.moviecatalogapi.exception;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(Long id) {
        super("Genre with id " + id + " not found");
    }
}
