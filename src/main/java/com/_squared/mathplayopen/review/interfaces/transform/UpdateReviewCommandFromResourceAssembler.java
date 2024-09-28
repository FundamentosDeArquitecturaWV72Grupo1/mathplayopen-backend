package com._squared.mathplayopen.review.interfaces.transform;

import com._squared.mathplayopen.review.domain.model.commands.UpdateReviewCommand;
import com._squared.mathplayopen.review.interfaces.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long reviewId, UpdateReviewResource reviewResource) {
        return new UpdateReviewCommand(reviewId, reviewResource.description(), reviewResource.score());
    }
}
