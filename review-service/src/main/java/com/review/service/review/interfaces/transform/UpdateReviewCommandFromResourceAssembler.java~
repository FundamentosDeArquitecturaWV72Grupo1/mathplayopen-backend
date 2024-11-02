package com.review.service.review.interfaces.transform;

import com.review.service.review.domain.model.commands.UpdateReviewCommand;
import com.review.service.review.interfaces.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long reviewId, UpdateReviewResource reviewResource) {
        return new UpdateReviewCommand(reviewId, reviewResource.description(), reviewResource.score());
    }
}
