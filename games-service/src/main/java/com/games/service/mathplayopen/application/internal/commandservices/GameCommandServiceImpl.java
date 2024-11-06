package com.games.service.mathplayopen.application.internal.commandservices;

import com.games.service.mathplayopen.application.external.feignclients.client.ExternalGameServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.client.UserServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import com.games.service.mathplayopen.domain.exceptions.FavoriteGameNotFoundException;
import com.games.service.mathplayopen.domain.exceptions.GameNotFoundException;
import com.games.service.mathplayopen.domain.model.commands.FavoriteGameCommand;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
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
import java.util.stream.Collectors;

@Service
public class GameCommandServiceImpl implements GameCommandService {
    private final FavoriteGameRepository favoriteGameRepository;
    private final UserServiceFeignClient userServiceClientFeignClient;
    private final ExternalGameServiceFeignClient externalGameServiceFeignClient;
    private final GameResourceAssembler gameResourceAssembler;
    private final GameRepository gameRepository;


    public GameCommandServiceImpl(GameRepository gameRepository, ExternalGameServiceFeignClient externalGameServiceFeignClient, FavoriteGameRepository favoriteGameRepository, UserServiceFeignClient userServiceClientFeignClient, GameResourceAssembler gameResourceAssembler) {
        this.gameRepository = gameRepository;
        this.externalGameServiceFeignClient = externalGameServiceFeignClient;
        this.favoriteGameRepository = favoriteGameRepository;
        this.userServiceClientFeignClient = userServiceClientFeignClient;
        this.gameResourceAssembler = gameResourceAssembler;
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

        /*
        List<GameResource> externalGames = externalGameServiceFeignClient.fetchGames();
        List<Game> games = externalGames.stream()
                .map(gameResourceAssembler::toEntity)
                .collect(Collectors.toList());
        return gameRepository.saveAll(games);*/
    }

    @Transactional
    @Override
    public FavoriteGame markGameAsFavorite(FavoriteGameCommand command) {
        //String token = userServiceClientFeignClient.getToken();
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJsdWlzaW5hZGUxMCIsImlhdCI6MTczMDUyNjE4MiwiZXhwIjoxNzMxMTMwOTgyfQ.VdgNOA0ryVZjepXd4DArMEr1KBxGE1veW9wXeFC2ZV3TbJxmFokvtVocpHztdHvM";

        /*
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("No token found");
        }
         */

        String authHeader = "Bearer " + token;
        UserDto userDtoFromService  = userServiceClientFeignClient.getCurrentUser (authHeader);
        if (userDtoFromService == null) {
            throw new RuntimeException("User  not authenticated");
        }

        UserDto user = mapToGameServiceUserDto(userDtoFromService);
        Game game = gameRepository.findById(command.gameId())
                .orElseThrow(() -> new GameNotFoundException(command.gameId()));

        Optional<FavoriteGame> existingFavorite = favoriteGameRepository.findByGameIdAndStudentId(command.gameId(), user.id());
        if (existingFavorite.isPresent()) {
            return existingFavorite.get();
        }

        FavoriteGame favoriteGame = new FavoriteGame(game, user.id());
        return favoriteGameRepository.save(favoriteGame);
    }

    @Transactional
    @Override
    public void removeGameFromFavorites(Long gameId, Long studentId) {
        FavoriteGame favoriteGame = favoriteGameRepository.findByGameIdAndStudentId(gameId, studentId)
                .orElseThrow(() -> new FavoriteGameNotFoundException(gameId, studentId));
        favoriteGameRepository.delete(favoriteGame);
    }

    private UserDto mapToGameServiceUserDto(UserDto userDto) {
        return new UserDto(userDto.id(), userDto.username());
    }
}
