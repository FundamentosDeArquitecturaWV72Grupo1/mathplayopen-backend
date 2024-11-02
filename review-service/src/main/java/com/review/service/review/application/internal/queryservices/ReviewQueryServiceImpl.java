package com.review.service.review.application.internal.queryservices;

import com.review.service.review.domain.model.aggregates.Review;
import com.review.service.review.domain.model.queries.GetAllReviewsQuery;
import com.review.service.review.domain.model.queries.GetReviewsByGameIdQuery;
import com.review.service.review.domain.services.ReviewQueryService;
import com.review.service.review.infrastructure.ReviewRepository;
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

    @Override
    public List<Review> handle(GetReviewsByGameIdQuery query) {
        return reviewRepository.findByGameId(query.gameId());
    }
}
