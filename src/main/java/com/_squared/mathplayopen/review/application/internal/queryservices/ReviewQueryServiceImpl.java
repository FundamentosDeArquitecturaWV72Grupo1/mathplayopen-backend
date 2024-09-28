package com._squared.mathplayopen.review.application.internal.queryservices;

import com._squared.mathplayopen.review.domain.model.aggregates.Review;
import com._squared.mathplayopen.review.domain.model.queries.GetAllReviewsQuery;
import com._squared.mathplayopen.review.domain.services.ReviewQueryService;
import com._squared.mathplayopen.review.infrastructure.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

}
