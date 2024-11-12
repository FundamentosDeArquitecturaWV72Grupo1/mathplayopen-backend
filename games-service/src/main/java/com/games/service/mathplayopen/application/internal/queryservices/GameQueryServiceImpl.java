package com.games.service.mathplayopen.application.internal.queryservices;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.domain.model.queries.*;
import com.games.service.mathplayopen.domain.services.GameQueryService;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.FavoriteGameRepository;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameRepository;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameQueryServiceImpl implements GameQueryService {
    private final GameRepository gameRepository;
    private final GameScoreRepository gameScoreRepository;
    private final FavoriteGameRepository favoriteGameRepository;

    public GameQueryServiceImpl(GameRepository gameRepository, GameScoreRepository gameScoreRepository, FavoriteGameRepository favoriteGameRepository) {
        this.gameRepository = gameRepository;
        this.gameScoreRepository = gameScoreRepository;
        this.favoriteGameRepository = favoriteGameRepository;
    }

    @Override
    public Optional<Game> handle(GetGameByIdQuery query) {
        return gameRepository.findById(query.gameId());
    }

    @Override
    public List<Game> handle(GetAllGamesQuery query) {
        return gameRepository.findAll();
    }

    @Override
    public List<FavoriteGame> handle(GetFavoriteGamesByStudentIdQuery query) {
        return favoriteGameRepository.findByStudentId(query.studentId());
    }

    @Override
    public Optional<Game> handle(GetGameByTitleQuery query) {
        return gameRepository.findByTitle(query.title());
    }

    @Override
    public List<GameScore> handle(GetAllScoresQuery query) {
        return gameScoreRepository.findAll();
    }
}
