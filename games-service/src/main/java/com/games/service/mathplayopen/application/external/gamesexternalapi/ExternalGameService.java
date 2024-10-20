package com.games.service.mathplayopen.application.external.gamesexternalapi;

import com.games.service.mathplayopen.domain.model.aggregates.Game;

import java.util.List;

public interface ExternalGameService {
    List<Game> fetchGames();
    Game fetchGameById(Long id);
}
