package com.review.service.review.domain.model.commands;

public record UpdateReviewCommand(Long id, String description, Integer score) {
}
