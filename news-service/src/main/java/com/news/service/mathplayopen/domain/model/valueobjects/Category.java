package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record Category(String category) {
    public Category {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("The news category cannot be null or empty.");
        }
    }

    @JsonValue
    public String getCategory() {
        return category;
    }
}
