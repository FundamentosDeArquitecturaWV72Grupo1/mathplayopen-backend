package com.games.service.mathplayopen.domain.model.entities;

import com.games.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "game_scores")
public class GameScore extends AuditableAbstractAggregateRoot<GameScore> {
    private Long studentId;
    private int score;

    public GameScore(Long studentId) {
        this.studentId = studentId;
        this.score = 0;
    }
}
