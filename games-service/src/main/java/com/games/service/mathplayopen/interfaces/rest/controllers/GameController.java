package com.games.service.mathplayopen.interfaces.rest.controllers;
import com.games.service.mathplayopen.application.internal.commandservices.GameCommandServiceImpl;
import com.games.service.mathplayopen.application.internal.dtos.FavoriteGameDto;
import com.games.service.mathplayopen.application.internal.dtos.GameDto;
import com.games.service.mathplayopen.application.internal.mappers.GameMapperServiceImpl;
import com.games.service.mathplayopen.application.internal.queryservices.GameQueryServiceImpl;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.commands.CreateGameCommand;
import com.games.service.mathplayopen.domain.model.queries.GetAllGamesQuery;
import com.games.service.mathplayopen.domain.model.queries.GetFavoriteGamesByStudentIdQuery;
import com.games.service.mathplayopen.domain.model.queries.GetGameByIdQuery;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final GameMapperServiceImpl gameMapperServiceImpl;
    private static final Logger log = LoggerFactory.getLogger(GameController.class);


    public GameController(GameCommandServiceImpl gameCommandService, GameQueryServiceImpl gameQueryService, GameMapperServiceImpl gameMapperServiceImpl) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
        this.gameMapperServiceImpl = gameMapperServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<GameDto> createGame(@RequestBody CreateGameCommand command) {
        Game game = gameCommandService.handle(command);
        return ResponseEntity.ok(gameMapperServiceImpl.toDto(game));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        return gameQueryService.handle(new GetGameByIdQuery(id))
                .map(game -> ResponseEntity.ok(gameMapperServiceImpl.toDto(game)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<Game> games = gameQueryService.handle(new GetAllGamesQuery());
        List<GameDto> gameDTOs = games.stream()
                .map(gameMapperServiceImpl::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameDTOs);
    }

    @PostMapping("/student/favorite-game/{gameId}")
    public ResponseEntity<FavoriteGameDto> markGameAsFavoriteGameByStudent(@PathVariable Long gameId, @RequestParam Long studentId) {
        FavoriteGame favoriteGame = gameCommandService.markGameAsFavorite(gameId, studentId);
        return ResponseEntity.ok(gameMapperServiceImpl.toFavoriteGameDto(favoriteGame));
    }

    @GetMapping("/student/favorite-games")
    public ResponseEntity<List<FavoriteGameDto>> getFavoriteGamesByStudentId(@RequestParam Long studentId) {
        List<FavoriteGame> favoriteGames = gameQueryService.handle(new GetFavoriteGamesByStudentIdQuery(studentId));
        List<FavoriteGameDto> favoriteGameDTOs = favoriteGames.stream()
                .map(gameMapperServiceImpl::toFavoriteGameDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(favoriteGameDTOs);
    }

    @PostMapping("/fetch-external-games-api")
    public ResponseEntity<List<GameDto>> fetchAndSaveExternalGames() {
        List<Game> games = gameCommandService.fetchAndSaveExternalGames();
        List<GameDto> gameDTOs = games.stream()
                .map(gameMapperServiceImpl::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameDTOs);
    }
}
