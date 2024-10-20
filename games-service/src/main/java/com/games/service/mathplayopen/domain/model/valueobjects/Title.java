package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record Title(String title) {
    public Title {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Game title cannot be null or empty");
        }
    }
}
