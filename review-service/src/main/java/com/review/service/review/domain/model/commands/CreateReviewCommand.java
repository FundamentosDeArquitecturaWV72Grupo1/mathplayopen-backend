package com.review.service.review.domain.model.commands;

public record CreateReviewCommand(String description, Integer score, Long gameId, String token) {
}
