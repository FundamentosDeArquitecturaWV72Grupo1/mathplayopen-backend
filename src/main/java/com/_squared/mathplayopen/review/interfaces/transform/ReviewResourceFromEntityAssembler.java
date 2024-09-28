package com._squared.mathplayopen.review.interfaces.transform;

import com._squared.mathplayopen.review.domain.model.aggregates.Review;
import com._squared.mathplayopen.review.interfaces.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(entity.getId(), entity.getDescription(), entity.getScore());
    }
}
