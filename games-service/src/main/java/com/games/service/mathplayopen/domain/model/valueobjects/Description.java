package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Description(String value) {
    public Description {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Game description cannot be null or empty");
        }
    }
}
