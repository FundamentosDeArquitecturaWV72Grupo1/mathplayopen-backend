package com.user.service.mathplayopen.domain.exceptions;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(Long aLong) {
        super("Could not find instructor " + aLong);
    }
}
