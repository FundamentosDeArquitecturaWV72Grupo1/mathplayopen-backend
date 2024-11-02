package com.games.service.mathplayopen.interfaces.rest.transform;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import com.games.service.mathplayopen.interfaces.rest.resources.FavoriteGameResource;

public class FavoriteGameResourceAssembler {
    public FavoriteGameResource toResource(FavoriteGame favoriteGame) {
        return new FavoriteGameResource(
                favoriteGame.getId(),
                favoriteGame.getGame().getId(),
                favoriteGame.getStudentId(),
                favoriteGame.getMarkedAsFavoriteAt()
        );
    }
}
