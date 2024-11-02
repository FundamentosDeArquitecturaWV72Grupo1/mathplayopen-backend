package com.review.service.review.domain.services;


import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.domain.model.queries.GetAllReviewsQuery;
import com.review.service.review.domain.model.queries.GetReviewsByGameIdQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    List<Review> handle(GetReviewsByGameIdQuery query);
}
