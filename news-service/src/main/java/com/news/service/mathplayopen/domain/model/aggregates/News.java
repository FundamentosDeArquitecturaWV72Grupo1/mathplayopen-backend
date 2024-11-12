package com.news.service.mathplayopen.domain.model.aggregates;

import com.news.service.mathplayopen.domain.model.valueobjects.*;
import com.news.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News extends AuditableAbstractAggregateRoot<News> {
    @Embedded
    private Author author;

    @Embedded
    private Title title;

    @Embedded
    private Description description;

    private String url;

    @Embedded
    private Source source;

    @Embedded
    private Image image;

    @Embedded
    private Category category;

    private Date publishedAt;
}
