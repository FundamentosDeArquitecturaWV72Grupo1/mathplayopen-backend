package com.user.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record EmailAddress(String email) {
    public EmailAddress {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
