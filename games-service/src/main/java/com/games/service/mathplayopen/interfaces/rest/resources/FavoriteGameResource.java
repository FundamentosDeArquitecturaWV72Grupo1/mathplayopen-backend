package com.games.service.mathplayopen.interfaces.rest.resources;

import java.time.LocalDateTime;

public record FavoriteGameResource(Long id, Long gameId, Long studentId, LocalDateTime markedAsFavoriteAt) {
}

