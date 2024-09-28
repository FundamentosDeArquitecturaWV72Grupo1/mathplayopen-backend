package com._squared.mathplayopen.review.domain.services;

import com._squared.mathplayopen.review.domain.model.aggregates.Review;
import com._squared.mathplayopen.review.domain.model.commands.CreateReviewCommand;
import com._squared.mathplayopen.review.domain.model.commands.DeleteReviewCommand;
import com._squared.mathplayopen.review.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    void handle(DeleteReviewCommand command);
    Optional<Review> handle(UpdateReviewCommand command);
}
