package com.games.service.mathplayopen.interfaces.rest;

import com.games.service.mathplayopen.application.external.feignclients.client.UserServiceFeignClient;
import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import com.games.service.mathplayopen.application.internal.commandservices.GameCommandServiceImpl;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.interfaces.rest.resources.GameScoreResource;
import com.games.service.mathplayopen.interfaces.rest.resources.ScoreUpdateRequest;
import com.games.service.mathplayopen.interfaces.rest.transform.GameScoreResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/scores", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Game Scores", description = "Games Score Management Endpoints")
//@CrossOrigin(origins = "http://localhost:4200")
public class ScoreController {
    private final GameCommandServiceImpl gameCommandService;
    private final UserServiceFeignClient userServiceFeignClient;

    public ScoreController(GameCommandServiceImpl gameCommandService, UserServiceFeignClient userServiceFeignClient) {
        this.gameCommandService = gameCommandService;
        this.userServiceFeignClient = userServiceFeignClient;
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

    //Cada vez que actualiza el puntaje que se envia debe sumarse a la puntuacion actual y en el Frontend debe actualizarse
    // reactivamente el puntaje apenas salgas del GameDetails
    @PostMapping("/update")
    public ResponseEntity<Void> updateScore(@RequestBody ScoreUpdateRequest scoreUpdateRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDto userDto = userServiceFeignClient.getCurrentUser (token);
        gameCommandService.updateStudentScore(userDto.id(), scoreUpdateRequest.getScore());
        return ResponseEntity.noContent().build();
    }
}
