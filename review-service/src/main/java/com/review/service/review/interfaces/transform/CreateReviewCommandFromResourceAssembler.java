package com.review.service.review.interfaces.transform;

import com.review.service.review.domain.model.commands.CreateReviewCommand;
import com.review.service.review.interfaces.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.description(), resource.score(), resource.gameId(), resource.token());
    }
}
