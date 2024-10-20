package com.games.service.mathplayopen.domain.services;

import com.games.service.mathplayopen.domain.model.commands.UpdateGameCoreCommand;

public interface GameScoreCommandService {
    void updateScore(UpdateGameCoreCommand command);
}
