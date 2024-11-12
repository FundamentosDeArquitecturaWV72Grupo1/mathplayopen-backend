package com.news.service.mathplayopen.interfaces.rest;

import com.news.service.mathplayopen.application.internal.commandservices.NewsCommandServiceImpl;
import com.news.service.mathplayopen.application.internal.queryservices.NewsQueryServiceImpl;
import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.commands.CreateNewsCommand;
import com.news.service.mathplayopen.domain.model.queries.GetAllNewsQuery;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByIdQuery;
import com.news.service.mathplayopen.interfaces.rest.resources.CreateNewsResource;
import com.news.service.mathplayopen.interfaces.rest.resources.NewsResource;
import com.news.service.mathplayopen.interfaces.rest.transform.CreateNewsCommandFromResourceAssembler;
import com.news.service.mathplayopen.interfaces.rest.transform.NewsResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "News", description = "News Management Endpoints")
public class NewsController {
    private final NewsCommandServiceImpl newsCommandService;
    private final NewsQueryServiceImpl newsQueryService;

    public NewsController(NewsCommandServiceImpl newsCommandService, NewsQueryServiceImpl newsQueryService) {
        this.newsCommandService = newsCommandService;
        this.newsQueryService = newsQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsResource>> getAllNews() {
        var getAllNews = new GetAllNewsQuery();
        var newsList = newsQueryService.handle(getAllNews);

        var newsResources = newsList.stream()
                .map(NewsResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(newsResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        var getNewsByIdQuery = new GetNewsByIdQuery(id);
        var news = newsQueryService.handle(getNewsByIdQuery);
        return news.map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("News not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<CreateNewsResource> createNews(@RequestBody CreateNewsResource news) {
        var createNewsCommand = CreateNewsCommandFromResourceAssembler.toCommandFromResource(news);
        var createdNews = newsCommandService.handle(createNewsCommand);

        return createdNews.map(created -> new CreateNewsResource(
                        created.getAuthor().getAuthor(),
                        created.getTitle().getTitle(),
                        created.getDescription().getDescription(),
                        created.getUrl(),
                        created.getSource().getSource(),
                        created.getImage().getImage(),
                        created.getCategory().getCategory()
                ))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("News not created"));
    }
}
