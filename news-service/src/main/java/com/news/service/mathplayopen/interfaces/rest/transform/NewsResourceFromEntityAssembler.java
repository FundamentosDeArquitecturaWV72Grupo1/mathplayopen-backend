package com.news.service.mathplayopen.interfaces.rest.transform;

import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.interfaces.rest.resources.NewsResource;

public class NewsResourceFromEntityAssembler {
    public static NewsResource toResourceFromEntity(News news) {
        return new NewsResource(
                news.getAuthor().getAuthor(),
                news.getTitle().getTitle(),
                news.getDescription().getDescription(),
                news.getUrl(),
                news.getSource().getSource(),
                news.getImage().getImage(),
                news.getCategory().getCategory(),
                news.getPublishedAt());
    }
}
