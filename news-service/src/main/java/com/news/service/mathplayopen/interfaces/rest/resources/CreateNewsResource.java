package com.news.service.mathplayopen.interfaces.rest.resources;

public record CreateNewsResource(
        String author,
        String title,
        String description,
        String url,
        String source,
        String image,
        String category
) {
}
