package com.review.service.review.application.internal.commandservices;

import com.review.service.review.application.external.clients.GameServiceFeignClient;
import com.review.service.review.application.external.clients.UserServiceFeignClient;
import com.review.service.review.application.external.dtos.GameDto;
import com.review.service.review.application.external.dtos.UserDto;
import com.review.service.review.domain.exceptions.GameNotFoundException;
import com.review.service.review.domain.exceptions.ReviewNotFoundException;
import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.domain.model.commands.CreateReviewCommand;
import com.review.service.review.domain.model.commands.DeleteReviewCommand;
import com.review.service.review.domain.model.commands.UpdateReviewCommand;
import com.review.service.review.domain.services.ReviewCommandService;
import com.review.service.review.infrastructure.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final GameServiceFeignClient gameServiceFeignClient;
    private final UserServiceFeignClient userServiceFeignClient;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, GameServiceFeignClient gameServiceFeignClient, UserServiceFeignClient userServiceFeignClient) {
        this.reviewRepository = reviewRepository;
        this.gameServiceFeignClient = gameServiceFeignClient;
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @Transactional
    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        GameDto game = gameServiceFeignClient.getGameById(command.gameId());
        if (game == null) {
            throw new GameNotFoundException(command.gameId());
        }
        GameDto mappedGame = mapToGameDto(game);

        String authHeader = "Bearer " + command.token();
        UserDto userDtoFromService  = userServiceFeignClient.getCurrentUser(authHeader);
        if (userDtoFromService == null) {
            throw new RuntimeException("User  not authenticated");
        }
        UserDto user = mapToGameServiceUserDto(userDtoFromService);

        Review review = new Review(command, mappedGame.getId(), user.id(), user.username());
        return Optional.of(reviewRepository.save(review));
    }

    private UserDto mapToGameServiceUserDto(UserDto userDto) {
        return new UserDto(userDto.id(), userDto.username());
    }

    @Transactional
    @Override
    public void handle(DeleteReviewCommand command) {
        Review review = reviewRepository.findById(command.reviewId())
                .orElseThrow(() -> new ReviewNotFoundException(command.reviewId()));
        reviewRepository.delete(review);
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        Review review = reviewRepository.findById(command.reviewId())
                .orElseThrow(() -> new ReviewNotFoundException(command.reviewId()));

        review.updateReview(command.description(), command.score());
        return Optional.of(reviewRepository.save(review));
    }

    private GameDto mapToGameDto(GameDto gameDtoFromService) {
        GameDto gameDto = new GameDto();
        gameDto.setId(gameDtoFromService.getId());
        gameDto.setTitle(gameDtoFromService.getTitle());
        gameDto.setDescription(gameDtoFromService.getDescription());
        gameDto.setEmbedCode(gameDtoFromService.getEmbedCode());
        gameDto.setImageUrl(gameDtoFromService.getImageUrl());
        gameDto.setRules(gameDtoFromService.getRules());
        gameDto.setTopic(gameDtoFromService.getTopic());
        return gameDto;
    }
}
