package com.review.service.review.interfaces.transform;

import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.interfaces.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(entity.getId(), entity.getDescription(), entity.getScore(), entity.getUserName());
    }
}
