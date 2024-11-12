package com.news.service.mathplayopen.domain.exceptions;

public class NewsCouldNotBeCreated extends RuntimeException {
    public NewsCouldNotBeCreated(String message) {
        super("The news could not be created: " + message);
    }
}
