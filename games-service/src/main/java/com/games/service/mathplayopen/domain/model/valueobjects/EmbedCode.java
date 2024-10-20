package com.games.service.mathplayopen.domain.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public record EmbedCode(String code) {
    public EmbedCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Embed code cannot be null or empty");
        }
    }
}
