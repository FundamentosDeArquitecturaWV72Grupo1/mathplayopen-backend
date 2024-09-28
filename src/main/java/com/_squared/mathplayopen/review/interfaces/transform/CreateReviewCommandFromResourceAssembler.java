package com._squared.mathplayopen.review.interfaces.transform;

import com._squared.mathplayopen.review.domain.model.commands.CreateReviewCommand;
import com._squared.mathplayopen.review.interfaces.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.description(), resource.score());
    }
}
