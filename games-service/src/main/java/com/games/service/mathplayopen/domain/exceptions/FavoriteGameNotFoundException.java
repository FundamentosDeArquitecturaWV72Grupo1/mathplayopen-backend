package com.games.service.mathplayopen.domain.exceptions;

public class FavoriteGameNotFoundException extends RuntimeException{
    public FavoriteGameNotFoundException(Long aLong) {
        super("Could not find favorite game " + aLong);
    }
}
