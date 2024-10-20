package com.games.service.mathplayopen.application.external.systemfacade;

import com.games.service.mathplayopen.application.external.gamesexternalapi.ExternalGameService;
import com.games.service.mathplayopen.domain.model.aggregates.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameSystemFacade {
    private final ExternalGameService externalGameService;

    public GameSystemFacade(ExternalGameService externalGameService) {
        this.externalGameService = externalGameService;
    }

    public List<Game> fetchExternalGames() {
        return externalGameService.fetchGames();
    }

    public Game fetchExternalGameById(Long id) {
        return externalGameService.fetchGameById(id);
    }
}
