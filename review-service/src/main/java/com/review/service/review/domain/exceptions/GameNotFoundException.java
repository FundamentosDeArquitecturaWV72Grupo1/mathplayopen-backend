package com.review.service.review.domain.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long gameId) {
        super("Game not found: " + gameId);
    }
}
