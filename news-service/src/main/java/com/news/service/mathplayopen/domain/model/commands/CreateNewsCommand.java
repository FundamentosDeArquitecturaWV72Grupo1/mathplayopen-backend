package com.news.service.mathplayopen.domain.model.commands;

import com.news.service.mathplayopen.domain.model.valueobjects.*;

public record CreateNewsCommand(
        Author author,
        Title title,
        Description description,
        String url,
        Source source,
        Image image,
        Category category
) {
}
