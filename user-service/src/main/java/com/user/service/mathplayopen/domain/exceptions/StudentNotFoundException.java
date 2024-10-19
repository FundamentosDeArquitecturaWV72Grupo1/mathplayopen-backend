package com.user.service.mathplayopen.domain.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long aLong) {
        super("Could not find student " + aLong);
    }
}
