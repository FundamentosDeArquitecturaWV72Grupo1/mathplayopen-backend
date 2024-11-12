package com.news.service.mathplayopen.interfaces.rest.transform;

import com.news.service.mathplayopen.domain.model.commands.CreateNewsCommand;
import com.news.service.mathplayopen.domain.model.valueobjects.*;
import com.news.service.mathplayopen.interfaces.rest.resources.CreateNewsResource;

public class CreateNewsCommandFromResourceAssembler {
    public static CreateNewsCommand toCommandFromResource(CreateNewsResource resource) {
        return new CreateNewsCommand(
                new Author(resource.author()),
                new Title(resource.title()),
                new Description(resource.description()),
                resource.url(),
                new Source(resource.source()),
                new Image(resource.image()),
                new Category(resource.category())
        );
    }
}
