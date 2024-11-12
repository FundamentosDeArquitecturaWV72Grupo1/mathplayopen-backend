package com.news.service.mathplayopen.application.internal.queryservices;

import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.queries.GetAllNewsQuery;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByIdQuery;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByTitleQuery;
import com.news.service.mathplayopen.domain.services.NewsQueryService;
import com.news.service.mathplayopen.infrastructure.persistance.jpa.respositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsQueryServiceImpl implements NewsQueryService {
    private final NewsRepository newsRepository;

    public NewsQueryServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> handle(GetAllNewsQuery query) {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> handle(GetNewsByIdQuery query) {
        return newsRepository.findById(query.newsId());
    }

    @Override
    public Optional<News> handle(GetNewsByTitleQuery query) {
        return newsRepository.findByTitle(query.title());
    }
}
