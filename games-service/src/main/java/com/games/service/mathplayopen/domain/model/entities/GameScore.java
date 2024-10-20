package com.games.service.mathplayopen.domain.model.entities;

import com.games.service.mathplayopen.domain.model.aggregates.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "game_scores")
@AllArgsConstructor
@NoArgsConstructor
public class GameScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private Long studentId;
    private Integer score;
    private LocalDateTime playedAt;
    private Long playTimeInSeconds;

    public GameScore(Game game, Long studentId, Integer score, Long playTimeInSeconds) {
        this.game = game;
        this.studentId = studentId;
        this.score = score;
        this.playTimeInSeconds = playTimeInSeconds;
        this.playedAt = LocalDateTime.now();
    }
}
