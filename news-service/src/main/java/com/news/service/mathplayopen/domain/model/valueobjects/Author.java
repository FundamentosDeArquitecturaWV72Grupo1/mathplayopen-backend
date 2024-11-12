package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Author(String author) {
    public Author {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("author of news cannot be null or empty");
        }
    }

    @JsonValue
    public String getAuthor() {
        return author;
    }
}
