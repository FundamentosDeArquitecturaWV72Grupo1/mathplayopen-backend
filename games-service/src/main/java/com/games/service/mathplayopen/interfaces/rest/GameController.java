package com.games.service.mathplayopen.interfaces.rest;
import com.games.service.mathplayopen.application.external.feignclients.TokenExtractor;
import com.games.service.mathplayopen.application.external.feignclients.client.UserServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import com.games.service.mathplayopen.application.internal.commandservices.GameCommandServiceImpl;
import com.games.service.mathplayopen.application.internal.commandservices.GameScoreServiceImpl;
import com.games.service.mathplayopen.application.internal.queryservices.GameQueryServiceImpl;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.commands.FavoriteGameCommand;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.queries.GetAllGamesQuery;
import com.games.service.mathplayopen.domain.model.queries.GetFavoriteGamesByStudentIdQuery;
import com.games.service.mathplayopen.domain.model.queries.GetGameByIdQuery;
import com.games.service.mathplayopen.interfaces.rest.resources.GameResource;
import com.games.service.mathplayopen.interfaces.rest.transform.GameResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/games", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Games", description = "Games Management Endpoints")
public class GameController {
    private final GameCommandServiceImpl gameCommandService;
    private final GameQueryServiceImpl gameQueryService;
    private final GameResourceAssembler gameResourceAssembler;
    private final UserServiceFeignClient userServiceFeignClient;
    private final TokenExtractor tokenExtractor;
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public GameController(GameCommandServiceImpl gameCommandService, GameQueryServiceImpl gameQueryService, GameResourceAssembler gameResourceAssembler, TokenExtractor tokenExtractor, UserServiceFeignClient userServiceFeignClient) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
        this.gameResourceAssembler = gameResourceAssembler;
        this.tokenExtractor = tokenExtractor;
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResource> getGameById(@PathVariable Long id) {
        return gameQueryService.handle(new GetGameByIdQuery(id))
                .map(game -> ResponseEntity.ok(gameResourceAssembler.toResource(game)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameResource>> getAllGames() {
        /*
        try {
            gameCommandService.fetchAndSaveExternalGames();
        } catch (Exception e) {
            System.err.println("Error when updating games from external API: " + e.getMessage());
        }
        */
        List<Game> games = gameQueryService.handle(new GetAllGamesQuery());
        List<GameResource> gameResources = games.stream()
                .map(gameResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameResources);
    }

    @GetMapping("/fetch-external-games")
    public ResponseEntity<List<GameResource>> fetchAndSaveExternalGames() {
        List<Game> savedGames = gameCommandService.fetchAndSaveExternalGames();
        List<GameResource> gameResources = savedGames.stream()
                .map(gameResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameResources);
    }

    @PostMapping("/student/favorite-game/{gameId}")
    public ResponseEntity<GameResource> markGameAsFavorite(
            @PathVariable Long gameId,
            @RequestHeader("Authorization") String token) {
        FavoriteGame favoriteGame = gameCommandService.markGameAsFavorite(new FavoriteGameCommand(gameId, token));
        return ResponseEntity.ok(gameResourceAssembler.toResource(favoriteGame.getGame()));
    }

    @GetMapping("/student/favorite-game/all")
    public ResponseEntity<List<GameResource>> getFavoriteGames(@RequestHeader("Authorization") String token) {
        UserDto user = userServiceFeignClient.getCurrentUser(token);
        List<FavoriteGame> favoriteGames = gameQueryService.handle(new GetFavoriteGamesByStudentIdQuery(user.id()));
        List<GameResource> gameResources = favoriteGames.stream()
                .map(favoriteGame -> gameResourceAssembler.toResource(favoriteGame.getGame()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameResources);
    }

    @DeleteMapping("/student/favorite-game/{gameId}")
    public ResponseEntity<Void> removeGameFromFavorites(
            @PathVariable Long gameId,
            @RequestHeader("Authorization") String token) {
        UserDto user = userServiceFeignClient.getCurrentUser(token);
        gameCommandService.removeGameFromFavorites(gameId, user.id());
        return ResponseEntity.noContent().build();
    }
}
