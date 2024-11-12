package com.games.service.mathplayopen.interfaces.rest;

import com.games.service.mathplayopen.application.external.feignclients.client.UserServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import com.games.service.mathplayopen.application.internal.commandservices.GameCommandServiceImpl;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.domain.model.queries.GetAllScoresQuery;
import com.games.service.mathplayopen.domain.services.GameQueryService;
import com.games.service.mathplayopen.interfaces.rest.resources.GameScoreResource;
import com.games.service.mathplayopen.interfaces.rest.resources.ScoreUpdateRequest;
import com.games.service.mathplayopen.interfaces.rest.transform.GameScoreResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/scores", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Game Scores", description = "Games Score Management Endpoints")
//@CrossOrigin(origins = "http://localhost:4200")
public class ScoreController {
    private final GameCommandServiceImpl gameCommandService;
    private final UserServiceFeignClient userServiceFeignClient;
    private final GameQueryService gameQueryService;

    public ScoreController(GameCommandServiceImpl gameCommandService, UserServiceFeignClient userServiceFeignClient, GameQueryService gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.userServiceFeignClient = userServiceFeignClient;
        this.gameQueryService = gameQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameScoreResource>> getAllScores() {
        var getAllScores = new GetAllScoresQuery();
        var scores = gameQueryService.handle(getAllScores);
        var scoresResource = scores.stream()
                .map(GameScoreResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(scoresResource);
    }

    @GetMapping("/current")
    public ResponseEntity<GameScoreResource> getCurrentScore(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDto userDto = userServiceFeignClient.getCurrentUser (token);
        GameScore gameScore = gameCommandService.getStudentScore(userDto.id());

        GameScoreResourceAssembler assembler = new GameScoreResourceAssembler();
        GameScoreResource resource = assembler.toResource(gameScore);

        return ResponseEntity.ok(resource);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateScore(@RequestBody ScoreUpdateRequest scoreUpdateRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDto userDto = userServiceFeignClient.getCurrentUser(token);
        gameCommandService.updateStudentScore(userDto.id(), scoreUpdateRequest.getScore());
        return ResponseEntity.noContent().build();
    }
}
