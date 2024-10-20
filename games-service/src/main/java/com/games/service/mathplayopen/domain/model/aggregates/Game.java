package com.games.service.mathplayopen.domain.model.aggregates;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
import com.games.service.mathplayopen.domain.model.events.GameUpdateEvent;
import com.games.service.mathplayopen.domain.model.valueobjects.Difficulty;
import com.games.service.mathplayopen.domain.model.valueobjects.EmbedCode;
import com.games.service.mathplayopen.domain.model.valueobjects.Description;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;
import com.games.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "games")
@AllArgsConstructor
@NoArgsConstructor
public class Game extends AuditableAbstractAggregateRoot<Game> {
    @Embedded
    private Title title;

    @Embedded
    private Description description;

    @Embedded
    private EmbedCode embedCode;

    private String imageUrl;
    private String rules;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String topic;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteGame> favoriteGames = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameScore> gameScores = new ArrayList<>();

    public void addFavorite(Long studentId) {
        FavoriteGame favoriteGame = new FavoriteGame(this, studentId);
        favoriteGames.add(favoriteGame);
    }

    public void removeFavorite(Long studentId) {
        favoriteGames.removeIf(fg -> fg.getStudentId().equals(studentId));
    }

    public void addScore(Long studentId, Integer score, Long playTimeInSeconds) {
        GameScore gameScore = new GameScore(this, studentId, score, playTimeInSeconds);
        gameScores.add(gameScore);
    }

    public void updateDetails(Title title, Description description, EmbedCode embedCode, String imageUrl, String rules, Difficulty difficulty, String topic) {
        this.title = title;
        this.description = description;
        this.embedCode = embedCode;
        this.imageUrl = imageUrl;
        this.rules = rules;
        this.difficulty = difficulty;
        this.topic = topic;
        registerEvent(new GameUpdateEvent(this.getId(), this.title, this.description));
    }
}
