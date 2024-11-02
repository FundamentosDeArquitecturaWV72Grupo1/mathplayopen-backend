package com.games.service.mathplayopen.domain.exceptions;

public class FavoriteGameNotFoundException extends RuntimeException{
    public FavoriteGameNotFoundException(Long aLong, Long studentId) {
        super("Could not find favorite game " + aLong + " for student " + studentId);
    }
}
