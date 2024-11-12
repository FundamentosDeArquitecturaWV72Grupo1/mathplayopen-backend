package com.news.service.mathplayopen.interfaces.rest.resources;

import java.util.Date;

public record NewsResource(
        String author,
        String title,
        String description,
        String url,
        String source,
        String image,
        String category,
        Date publishedAt
) {
}
