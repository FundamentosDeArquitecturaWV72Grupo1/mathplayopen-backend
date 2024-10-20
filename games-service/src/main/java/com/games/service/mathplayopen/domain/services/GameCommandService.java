package com.games.service.mathplayopen.domain.services;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.commands.CreateGameCommand;

import java.util.List;

public interface GameCommandService {
    Game handle(CreateGameCommand command);
    List<Game> fetchAndSaveExternalGames();
    FavoriteGame markGameAsFavorite(Long gameId, Long studentId);
}
