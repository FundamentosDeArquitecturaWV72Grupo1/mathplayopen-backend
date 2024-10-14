package com.review.service.review.domain.services;

import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.domain.model.commands.CreateReviewCommand;
import com.review.service.review.domain.model.commands.DeleteReviewCommand;
import com.review.service.review.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    void handle(DeleteReviewCommand command);
    Optional<Review> handle(UpdateReviewCommand command);
}
