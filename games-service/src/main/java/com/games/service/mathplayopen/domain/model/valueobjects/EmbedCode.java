package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record EmbedCode(String value) {
    public EmbedCode {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Embed code cannot be null or empty");
        }
    }
}
