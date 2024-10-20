package com.games.service.mathplayopen.domain.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(Long aLong) {
        super("Could not find game " + aLong);
    }
}
