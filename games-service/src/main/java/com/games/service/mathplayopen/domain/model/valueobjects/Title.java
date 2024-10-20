package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Title(String value) {
    public Title {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Game title cannot be null or empty");
        }
    }
}
