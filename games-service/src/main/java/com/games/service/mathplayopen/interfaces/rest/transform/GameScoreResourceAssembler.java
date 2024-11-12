package com.games.service.mathplayopen.interfaces.rest.transform;

import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.interfaces.rest.resources.GameScoreResource;

public class GameScoreResourceAssembler {
    public static GameScoreResource toResource(GameScore gameScore) {
        return new GameScoreResource(
                gameScore.getStudentId(),
                gameScore.getScore()
        );
    }
}
