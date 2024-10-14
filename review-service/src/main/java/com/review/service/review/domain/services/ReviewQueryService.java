package com.review.service.review.domain.services;


import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.domain.model.queries.GetAllReviewsQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
}
