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
@Entity(name = "favorite_games")
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private Long studentId;
    private LocalDateTime markedAsFavoriteAt;

    public FavoriteGame(Game game, Long studentId) {
        this.game = game;
        this.studentId = studentId;
        this.markedAsFavoriteAt = LocalDateTime.now();
    }
}
