package com.review.service.review.domain.model.aggregates;

import com.review.service.review.domain.model.commands.CreateReviewCommand;
import com.review.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "review")
@NoArgsConstructor
public class Review extends AuditableAbstractAggregateRoot<Review>{
    @NotEmpty
    private String description;

    @Min(1)
    @Max(5)
    private Integer score;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "user_id")
    private Long userId;

    public Review(CreateReviewCommand command, Long gameId, Long userId) {
        this.description = command.description();
        this.score = command.score();
        this.gameId = gameId;
        this.userId = userId;
    }

    public Review updateReview(String description, Integer score) {
        this.description = description;
        this.score = score;
        return this;
    }
}
