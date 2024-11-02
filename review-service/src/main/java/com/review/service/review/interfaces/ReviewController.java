package com.review.service.review.interfaces;

import com.review.service.review.domain.model.commands.DeleteReviewCommand;
import com.review.service.review.domain.model.queries.GetAllReviewsQuery;
import com.review.service.review.domain.model.queries.GetReviewsByGameIdQuery;
import com.review.service.review.domain.services.ReviewCommandService;
import com.review.service.review.domain.services.ReviewQueryService;
import com.review.service.review.interfaces.resources.CreateReviewResource;
import com.review.service.review.interfaces.resources.ReviewResource;
import com.review.service.review.interfaces.resources.UpdateReviewResource;
import com.review.service.review.interfaces.transform.CreateReviewCommandFromResourceAssembler;
import com.review.service.review.interfaces.transform.ReviewResourceFromEntityAssembler;
import com.review.service.review.interfaces.transform.UpdateReviewCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        var getAllReviews = new GetAllReviewsQuery();
        var reviews = reviewQueryService.handle(getAllReviews);
        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reviewResources);
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource createReviewResource) {
        var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(createReviewResource);
        var review = reviewCommandService.handle(createReviewCommand);

        if (review.isPresent()) {
            var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
            return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewResource updateReviewResource) {
        var updateReviewCommand = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(reviewId, updateReviewResource);
        var review = reviewCommandService.handle(updateReviewCommand);

        if (review.isPresent()) {
            var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
            return ResponseEntity.ok(reviewResource);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        var deleteReviewCommand = new DeleteReviewCommand(reviewId);
        reviewCommandService.handle(deleteReviewCommand);
        return ResponseEntity.ok("Review deleted successfully");
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByGameId(@PathVariable Long gameId) {
        var reviews = reviewQueryService.handle(new GetReviewsByGameIdQuery(gameId));
        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reviewResources);
    }
}
