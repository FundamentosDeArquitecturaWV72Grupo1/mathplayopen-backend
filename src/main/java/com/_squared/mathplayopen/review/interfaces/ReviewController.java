package com._squared.mathplayopen.review.interfaces;

import com._squared.mathplayopen.review.domain.model.commands.DeleteReviewCommand;
import com._squared.mathplayopen.review.domain.model.queries.GetAllReviewsQuery;
import com._squared.mathplayopen.review.domain.services.ReviewCommandService;
import com._squared.mathplayopen.review.domain.services.ReviewQueryService;
import com._squared.mathplayopen.review.interfaces.resources.CreateReviewResource;
import com._squared.mathplayopen.review.interfaces.resources.ReviewResource;
import com._squared.mathplayopen.review.interfaces.resources.UpdateReviewResource;
import com._squared.mathplayopen.review.interfaces.transform.CreateReviewCommandFromResourceAssembler;
import com._squared.mathplayopen.review.interfaces.transform.ReviewResourceFromEntityAssembler;
import com._squared.mathplayopen.review.interfaces.transform.UpdateReviewCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews(){
        var getAllReviews = new GetAllReviewsQuery();
        var reviews = reviewQueryService.handle(getAllReviews);
        var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reviewResources);
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource createReviewResource){
        var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(createReviewResource);
        var review = reviewCommandService.handle(createReviewCommand);
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return new ResponseEntity<ReviewResource>(reviewResource, HttpStatus.CREATED);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewResource updateReviewResource){
        var updateReviewCommand = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(reviewId, updateReviewResource);
        var review = reviewCommandService.handle(updateReviewCommand);
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId){
        var deleteReviewCommand = new DeleteReviewCommand(reviewId);
        reviewCommandService.handle(deleteReviewCommand);
        return ResponseEntity.ok("Delete review");
    }

}
