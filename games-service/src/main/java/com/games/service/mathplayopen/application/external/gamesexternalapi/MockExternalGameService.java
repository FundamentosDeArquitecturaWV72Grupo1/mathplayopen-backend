package com.games.service.mathplayopen.application.external.gamesexternalapi;

import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.valueobjects.Description;
import com.games.service.mathplayopen.domain.model.valueobjects.Difficulty;
import com.games.service.mathplayopen.domain.model.valueobjects.EmbedCode;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MockExternalGameService implements ExternalGameService{
    @Override
    public List<Game> fetchGames() {
        return Arrays.asList(
                new Game(
                        new Title("Math Puzzle 1"),
                        new Description("Solve basic arithmetic"),
                        new EmbedCode("<embed>code1</embed>"),
                        "image1.jpg",
                        "Solve in 60 seconds",
                        Difficulty.EASY,
                        "Arithmetic"
                ),
                new Game(
                        new Title("Geometry Challenge"),
                        new Description("Identify shapes"),
                        new EmbedCode("<embed>code2</embed>"),
                        "image2.jpg",
                        "Match shapes to names",
                        Difficulty.MEDIUM,
                        "Geometry"
                )
        );
    }

    @Override
    public Game fetchGameById(Long id) {
        return new Game(
                new Title("SUMA Y RESTA SEGÚN CORRESPONDA"),
                new Description("Solve basic arithmetic"),
                new EmbedCode("<iframe allow=\"fullscreen; autoplay;\" allowfullscreen width=\"795\" height=\"690\" frameborder=\"0\" src=\"https://es.educaplay.com/juego/5335947-la_resta.html\"></iframe>"),
                "game1.jpg",
                "Solve in 60 seconds",
                Difficulty.EASY,
                "Arithmetic / Crossword"
        );
    }
}
