package com.games.service.mathplayopen.interfaces.rest.resources;

public record GameResource(
        Long id,
        String title,
        String description,
        String embedCode,
        String imageUrl,
        String rules,
        String topic
) {}
