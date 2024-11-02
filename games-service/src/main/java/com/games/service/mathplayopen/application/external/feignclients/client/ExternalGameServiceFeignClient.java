package com.games.service.mathplayopen.application.external.feignclients.client;

import com.games.service.mathplayopen.application.external.feignclients.FeignClientConfig;
import com.games.service.mathplayopen.interfaces.rest.resources.GameResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "game-external-service", url = "http://localhost:9085", configuration = FeignClientConfig.class)
public interface ExternalGameServiceFeignClient {
    @GetMapping("/api/v1/games-external/all")
    List<GameResource> fetchGames();
}
