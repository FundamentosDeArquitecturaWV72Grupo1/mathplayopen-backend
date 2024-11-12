package com.news.service.mathplayopen.application.internal.commandservices;

import com.news.service.mathplayopen.domain.exceptions.NewsCouldNotBeCreated;
import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.commands.CreateNewsCommand;
import com.news.service.mathplayopen.domain.services.NewsCommandService;
import com.news.service.mathplayopen.infrastructure.persistance.jpa.respositories.NewsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NewsCommandServiceImpl implements NewsCommandService {
    private final NewsRepository newsRepository;

    public NewsCommandServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Transactional
    @Override
    public Optional<News> handle(CreateNewsCommand command) {
        News news = new News();
        news.setAuthor(command.author());
        news.setTitle(command.title());
        news.setDescription(command.description());
        news.setUrl(command.url());
        news.setSource(command.source());
        news.setImage(command.image());
        news.setCategory(command.category());
        news.setPublishedAt(new Date());
        try {
            return Optional.ofNullable(newsRepository.save(news));
        } catch (Exception e) {
            throw new NewsCouldNotBeCreated(e.getMessage());
        }
    }
}
