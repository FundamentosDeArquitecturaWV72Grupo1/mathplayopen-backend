package com._squared.mathplayopen.review.domain.services;


import com._squared.mathplayopen.review.domain.model.aggregates.Review;
import com._squared.mathplayopen.review.domain.model.queries.GetAllReviewsQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
}
