package com.games.service.mathplayopen.interfaces.rest.resources;

public record GameResource(
        Long id,
        String title,
        String description,
        String embedCode,
        String image,
        String rules,
        String topic
) {}
