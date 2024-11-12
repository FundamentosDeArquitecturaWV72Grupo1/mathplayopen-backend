package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record Title(String title) {
    public Title {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The news title cannot be null or empty.");
        }
    }

    @JsonValue
    public String getTitle() {
        return title;
    }
}
