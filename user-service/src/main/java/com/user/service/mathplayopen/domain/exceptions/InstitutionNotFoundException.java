package com.user.service.mathplayopen.domain.exceptions;

public class InstitutionNotFoundException extends RuntimeException {

    public InstitutionNotFoundException(Long aLong) {
        super("Could not find institution " + aLong);
    }
}
