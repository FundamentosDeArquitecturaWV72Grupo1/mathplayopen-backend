package com.review.service.review.domain.model.commands;

public record UpdateReviewCommand(Long reviewId, String description, Integer score) {
}
