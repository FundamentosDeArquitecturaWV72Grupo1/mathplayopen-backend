package com.news.service.mathplayopen.domain.services;

import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.queries.GetAllNewsQuery;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByIdQuery;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByTitleQuery;

import java.util.List;
import java.util.Optional;

public interface NewsQueryService {
    List<News> handle(GetAllNewsQuery query);
    Optional<News> handle(GetNewsByIdQuery query);
    Optional<News> handle(GetNewsByTitleQuery query);
}
