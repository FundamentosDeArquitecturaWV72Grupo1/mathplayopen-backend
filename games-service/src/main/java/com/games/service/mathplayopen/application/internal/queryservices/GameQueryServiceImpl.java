package com.games.service.mathplayopen.application.internal.queryservices;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.queries.*;
import com.games.service.mathplayopen.domain.services.GameQueryService;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.FavoriteGameRepository;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameQueryServiceImpl implements GameQueryService {
    private final GameRepository gameRepository;
    private final FavoriteGameRepository favoriteGameRepository;

    public GameQueryServiceImpl(GameRepository gameRepository, FavoriteGameRepository favoriteGameRepository) {
        this.gameRepository = gameRepository;
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
}
