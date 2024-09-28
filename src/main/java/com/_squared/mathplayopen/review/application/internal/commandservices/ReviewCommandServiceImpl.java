package com._squared.mathplayopen.review.application.internal.commandservices;

import com._squared.mathplayopen.review.domain.model.aggregates.Review;
import com._squared.mathplayopen.review.domain.model.commands.CreateReviewCommand;
import com._squared.mathplayopen.review.domain.model.commands.DeleteReviewCommand;
import com._squared.mathplayopen.review.domain.model.commands.UpdateReviewCommand;
import com._squared.mathplayopen.review.domain.services.ReviewCommandService;
import com._squared.mathplayopen.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command){
        var review = new Review(command);
        reviewRepository.save(review);
        return Optional.of(review);
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command){
        var result = reviewRepository.findById(command.id());
        var reviewToUpdate = result.get();
        var updateReview = reviewRepository.save(reviewToUpdate.UpdateReview(command.description(),command.score()));
        return Optional.of(updateReview);
    }

    @Override
    public void handle(DeleteReviewCommand command){
        reviewRepository.deleteById(command.reviewId());
    }

}
