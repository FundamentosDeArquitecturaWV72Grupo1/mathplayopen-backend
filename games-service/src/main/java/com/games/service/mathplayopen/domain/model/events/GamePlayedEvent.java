package com.games.service.mathplayopen.domain.model.events;

public record GamePlayedEvent(
        Long gameId,
        Long studentId,
        Long playTimeInSeconds
) { }
