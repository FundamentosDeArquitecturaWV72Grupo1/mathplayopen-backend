package com.games.service.mathplayopen.application.internal.commandservices;

import com.games.service.mathplayopen.application.external.feignclients.client.ExternalGameServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.client.UserServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import com.games.service.mathplayopen.domain.exceptions.FavoriteGameNotFoundException;
import com.games.service.mathplayopen.domain.exceptions.GameNotFoundException;
import com.games.service.mathplayopen.domain.model.commands.FavoriteGameCommand;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;
import com.games.service.mathplayopen.domain.services.GameCommandService;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.FavoriteGameRepository;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameRepository;
import com.games.service.mathplayopen.interfaces.rest.resources.GameResource;
import com.games.service.mathplayopen.interfaces.rest.transform.GameResourceAssembler;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameCommandServiceImpl implements GameCommandService {
    private final FavoriteGameRepository favoriteGameRepository;
    private final UserServiceFeignClient userServiceClientFeignClient;
    private final ExternalGameServiceFeignClient externalGameServiceFeignClient;
    private final GameResourceAssembler gameResourceAssembler;
    private final GameRepository gameRepository;
    private final GameScoreServiceImpl gameScoreService;


    public GameCommandServiceImpl(GameRepository gameRepository, ExternalGameServiceFeignClient externalGameServiceFeignClient, FavoriteGameRepository favoriteGameRepository, UserServiceFeignClient userServiceClientFeignClient, GameResourceAssembler gameResourceAssembler, GameScoreServiceImpl gameScoreService) {
        this.gameRepository = gameRepository;
        this.externalGameServiceFeignClient = externalGameServiceFeignClient;
        this.favoriteGameRepository = favoriteGameRepository;
        this.userServiceClientFeignClient = userServiceClientFeignClient;
        this.gameResourceAssembler = gameResourceAssembler;
        this.gameScoreService = gameScoreService;
    }

    @Transactional
    @Override
    public List<Game> fetchAndSaveExternalGames() {
        List<GameResource> externalGames = externalGameServiceFeignClient.fetchGames();
        List<Game> games = new ArrayList<>();

        for (GameResource resource : externalGames) {
            Title title = new Title(resource.title());
            if (gameRepository.findByTitle(title).isEmpty()) {
                Game game = gameResourceAssembler.toEntity(resource);
                games.add(game);
            }
        }
        return gameRepository.saveAll(games);
    }

    @Transactional
    @Override
    public FavoriteGame markGameAsFavorite(FavoriteGameCommand command) {
        String authHeader = "Bearer " + command.token();
        UserDto userDtoFromService = userServiceClientFeignClient.getCurrentUser(authHeader);

        if (userDtoFromService == null) {
            throw new RuntimeException("User not authenticated");
        }

        Game game = gameRepository.findById(command.gameId())
                .orElseThrow(() -> new GameNotFoundException(command.gameId()));

        Optional<FavoriteGame> existingFavorite = favoriteGameRepository.findByGameIdAndStudentId(command.gameId(), userDtoFromService.id());
        if (existingFavorite.isPresent()) {
            return existingFavorite.get();
        }

        FavoriteGame favoriteGame = new FavoriteGame(game, userDtoFromService.id());
        return favoriteGameRepository.save(favoriteGame);
    }

    @Transactional
    @Override
    public void removeGameFromFavorites(Long gameId, Long studentId) {
        FavoriteGame favoriteGame = favoriteGameRepository.findByGameIdAndStudentId(gameId, studentId)
                .orElseThrow(() -> new FavoriteGameNotFoundException(gameId, studentId));
        favoriteGameRepository.delete(favoriteGame);
    }

    @Transactional
    @Override
    public void updateStudentScore(Long studentId, int score) {
        gameScoreService.updateScore(studentId, score);
    }

    @Override
    public GameScore getStudentScore(Long studentId) {
        return gameScoreService.getScoreByStudentId(studentId);
    }
}
