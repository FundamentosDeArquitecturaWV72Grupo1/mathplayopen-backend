package com.review.service.review.application.external.clients;

import com.review.service.review.application.external.dtos.GameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service", url = "http://localhost:9095")
public interface GameServiceFeignClient {
    @GetMapping("/api/v1/games/{id}")
    GameDto getGameById(@PathVariable Long id);
}
