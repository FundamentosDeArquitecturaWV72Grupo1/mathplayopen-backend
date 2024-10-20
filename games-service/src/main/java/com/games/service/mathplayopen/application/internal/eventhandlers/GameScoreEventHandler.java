package com.games.service.mathplayopen.application.internal.eventhandlers;

import com.games.service.mathplayopen.application.internal.commandservices.GameScoreCommandServiceImpl;
import com.games.service.mathplayopen.domain.model.commands.UpdateGameCoreCommand;
import com.games.service.mathplayopen.domain.model.events.GamePlayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameScoreEventHandler {
    private final GameScoreCommandServiceImpl gameScoreService;

    public GameScoreEventHandler(GameScoreCommandServiceImpl gameScoreService) {
        this.gameScoreService = gameScoreService;
    }

    @EventListener
    public void handleGamePlayedEvent(GamePlayedEvent event) {
        UpdateGameCoreCommand command = new UpdateGameCoreCommand(
                event.gameId(),
                event.studentId(),
                event.playTimeInSeconds()
        );
        gameScoreService.updateScore(command);
    }
}
