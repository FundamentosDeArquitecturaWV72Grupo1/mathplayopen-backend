package com.games.service.mathplayopen.domain.services;

import com.games.service.mathplayopen.application.internal.dtos.FavoriteGameDto;
import com.games.service.mathplayopen.application.internal.dtos.GameDto;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;

public interface GameMapperService {
    Game toEntity(GameDto dto);
    GameDto toDto(Game game);
    FavoriteGameDto toFavoriteGameDto(FavoriteGame favoriteGame);
}
