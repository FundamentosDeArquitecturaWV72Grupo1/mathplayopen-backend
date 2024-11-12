package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record Description(String description) {
    public Description {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("The news description cannot be null or empty.");
        }
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
