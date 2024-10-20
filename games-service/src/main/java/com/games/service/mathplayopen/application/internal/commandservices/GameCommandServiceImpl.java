package com.games.service.mathplayopen.application.internal.commandservices;

import com.games.service.mathplayopen.application.external.systemfacade.GameSystemFacade;
import com.games.service.mathplayopen.application.external.gamesexternalapi.ExternalGameService;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.commands.CreateGameCommand;
import com.games.service.mathplayopen.domain.services.GameCommandService;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.FavoriteGameRepository;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameCommandServiceImpl implements GameCommandService {
    private final GameRepository gameRepository;
    private final ExternalGameService externalGameService;
    private final FavoriteGameRepository favoriteGameRepository;
    private final GameSystemFacade gameSystemFacade;

    public GameCommandServiceImpl(GameRepository gameRepository, ExternalGameService externalGameService, FavoriteGameRepository favoriteGameRepository, GameSystemFacade gameSystemFacade) {
        this.gameRepository = gameRepository;
        this.externalGameService = externalGameService;
        this.favoriteGameRepository = favoriteGameRepository;
        this.gameSystemFacade = gameSystemFacade;
    }

    @Transactional
    @Override
    public Game handle(CreateGameCommand command) {
        Game game = new Game(
                command.title(),
                command.description(),
                command.embedCode(),
                command.imageUrl(),
                command.rules(),
                command.difficulty(),
                command.topic()
        );
        return gameRepository.save(game);
    }

    @Transactional
    @Override
    public FavoriteGame markGameAsFavorite(Long gameId, Long studentId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        Optional<FavoriteGame> existingFavorite = favoriteGameRepository.findByGameIdAndStudentId(gameId, studentId);
        if (existingFavorite.isPresent()) {
            return existingFavorite.get();
        }

        FavoriteGame favoriteGame = new FavoriteGame(game, studentId);
        return favoriteGameRepository.save(favoriteGame);
    }

    @Transactional
    @Override
    public List<Game> fetchAndSaveExternalGames(){
        List<Game> externalGames = gameSystemFacade.fetchExternalGames();
        return externalGames.stream()
                .map(gameRepository::save)
                .collect(Collectors.toList());
    }
}
