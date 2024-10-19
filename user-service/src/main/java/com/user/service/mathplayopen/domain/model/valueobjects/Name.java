package com.user.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Name(String firstName, String lastName) {
    public Name {
        if (firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
