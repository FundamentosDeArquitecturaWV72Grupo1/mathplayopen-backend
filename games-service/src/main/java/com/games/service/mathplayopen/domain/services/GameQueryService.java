package com.games.service.mathplayopen.domain.services;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.queries.GetAllGamesQuery;
import com.games.service.mathplayopen.domain.model.queries.GetFavoriteGamesByStudentIdQuery;
import com.games.service.mathplayopen.domain.model.queries.GetGameByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GameQueryService {
    Optional<Game> handle(GetGameByIdQuery query);
    List<Game> handle(GetAllGamesQuery query);
    List<FavoriteGame> handle(GetFavoriteGamesByStudentIdQuery query);
}
