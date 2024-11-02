package com.review.service.review.interfaces.resources;

public record CreateReviewResource(String description, Integer score, Long gameId, String token) {
}
