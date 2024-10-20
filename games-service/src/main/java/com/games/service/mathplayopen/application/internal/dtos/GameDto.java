package com.games.service.mathplayopen.application.internal.dtos;

import com.games.service.mathplayopen.domain.model.valueobjects.Difficulty;

public record GameDto(
        Long id,
        String title,
        String description,
        String embedCode,
        String imageUrl,
        String rules,
        Difficulty difficulty,
        String topic
) { }
