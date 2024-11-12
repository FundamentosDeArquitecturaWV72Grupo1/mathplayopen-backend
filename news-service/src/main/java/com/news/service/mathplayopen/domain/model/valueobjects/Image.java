package com.news.service.mathplayopen.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record Image (String image) {
    public Image {
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("The news image cannot be null or empty.");
        }
    }

    @JsonValue
    public String getImage() {
        return image;
    }
}
