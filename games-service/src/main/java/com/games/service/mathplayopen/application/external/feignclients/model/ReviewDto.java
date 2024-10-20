package com.games.service.mathplayopen.application.external.feignclients.model;

import java.time.LocalDateTime;

public record ReviewDto (
        Long id,
        String description,
        int score,
        Long gameId,
        Long studentId,
        LocalDateTime createdAt
) { }
