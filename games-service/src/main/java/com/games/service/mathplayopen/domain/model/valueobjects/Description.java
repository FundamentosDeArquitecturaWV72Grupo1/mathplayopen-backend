package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Description(String description) {
    public Description {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Game description cannot be null or empty");
        }
    }
}
