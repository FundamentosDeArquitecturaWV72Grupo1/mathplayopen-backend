package com.games.service.mathplayopen.domain.model.aggregates;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.entities.GameScore;
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

    private String image;
    private String rules;
    private String topic;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteGame> favoriteGames = new ArrayList<>();

    public Game(Title title, Description description, EmbedCode embedCode, String image, String rules, String topic) {
        this.title = title;
        this.description = description;
        this.embedCode = embedCode;
        this.image = image;
        this.rules = rules;
        this.topic = topic;
    }
}
