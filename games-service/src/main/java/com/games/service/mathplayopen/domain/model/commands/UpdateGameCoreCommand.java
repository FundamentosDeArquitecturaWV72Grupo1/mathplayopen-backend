package com.games.service.mathplayopen.domain.model.commands;

public record UpdateGameCoreCommand(
    Long gameId,
    Long studentId,
    Long playTimeInSeconds
) { }
