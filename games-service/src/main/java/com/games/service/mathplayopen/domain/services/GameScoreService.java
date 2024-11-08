package com.games.service.mathplayopen.domain.services;

import com.games.service.mathplayopen.domain.model.entities.GameScore;

public interface GameScoreService {
    GameScore getScoreByStudentId(Long studentId);
    void updateScore(Long studentId, int newScore);
}
