package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record Source(String source) {
    public Source {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("The news source cannot be null or empty.");
        }
    }

    @JsonValue
    public String getSource() {
        return source;
    }
}
