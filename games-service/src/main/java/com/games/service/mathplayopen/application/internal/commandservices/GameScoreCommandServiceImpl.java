package com.games.service.mathplayopen.application.internal.commandservices;

import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.commands.UpdateGameCoreCommand;
import com.games.service.mathplayopen.domain.services.GameScoreCommandService;
import com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameScoreCommandServiceImpl implements GameScoreCommandService {
    private final GameRepository gameRepository;

    public GameScoreCommandServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void updateScore(UpdateGameCoreCommand command) {
        Game game = gameRepository.findById(command.gameId())
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        int score = calculateScore(command.playTimeInSeconds());
        game.addScore(command.studentId(), score, command.playTimeInSeconds());

        gameRepository.save(game);
    }

    private int calculateScore(Long playTimeInSeconds) {
        //TODO: lógica de cálculo de puntuación
        //Example: 1 punto por cada 10 segundos de juego
        return Math.toIntExact(playTimeInSeconds / 10);
    }
}
