package com.games.service.mathplayopen.application.internal.mappers;

import com.games.service.mathplayopen.application.internal.dtos.FavoriteGameDto;
import com.games.service.mathplayopen.application.internal.dtos.GameDto;
import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import com.games.service.mathplayopen.domain.model.valueobjects.Description;
import com.games.service.mathplayopen.domain.model.valueobjects.EmbedCode;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;
import com.games.service.mathplayopen.domain.services.GameMapperService;
import org.springframework.stereotype.Service;

@Service
public class GameMapperServiceImpl implements GameMapperService {

    @Override
    public Game toEntity(GameDto dto) {
        return new Game(
                new Title(dto.title()),
                new Description(dto.description()),
                new EmbedCode(dto.embedCode()),
                dto.imageUrl(),
                dto.rules(),
                dto.difficulty(),
                dto.topic()
        );
    }

    @Override
    public GameDto toDto(Game game) {
        return new GameDto(
                game.getId(),
                game.getTitle().value(),
                game.getDescription().value(),
                game.getEmbedCode().value(),
                game.getImageUrl(),
                game.getRules(),
                game.getDifficulty(),
                game.getTopic()
        );
    }

    @Override
    public FavoriteGameDto toFavoriteGameDto(FavoriteGame favoriteGame) {
        return new FavoriteGameDto(
                favoriteGame.getId(),
                favoriteGame.getGame().getId(),
                favoriteGame.getStudentId()
        );
    }
}

