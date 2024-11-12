package com.news.service.mathplayopen.domain.services;

import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.commands.CreateNewsCommand;
import java.util.Optional;

public interface NewsCommandService {
    Optional<News> handle(CreateNewsCommand command);
}
