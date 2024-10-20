package com.games.service.mathplayopen.application.external.feignclients.client;

import com.games.service.mathplayopen.application.external.feignclients.model.ReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "review-service")
public interface ReviewServiceFeignClient {
    @GetMapping("/api/v1/reviews/game/{gameId}")
    List<ReviewDto> getReviewsByGameId(@PathVariable Long gameId);
}
