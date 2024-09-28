package com._squared.mathplayopen.review.domain.model.aggregates;

import com._squared.mathplayopen.review.domain.model.commands.CreateReviewCommand;
import com._squared.mathplayopen.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Review extends AuditableAbstractAggregateRoot<Review>{

    @NotEmpty
    private String description;

    @Min(1)
    @Max(5)
    private int score;


    public Review(CreateReviewCommand command) {
        this.description = command.description();
        this.score = command.score();
    }

    public Review UpdateReview(String description, Integer score) {
        this.description = description;
        this.score = score;
        return this;
    }
}
