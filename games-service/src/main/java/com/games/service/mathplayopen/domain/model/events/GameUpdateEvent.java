package com.games.service.mathplayopen.domain.model.events;

import com.games.service.mathplayopen.domain.model.valueobjects.Description;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;

public record GameUpdateEvent(
        Long id,
        Title title,
        Description description
) { }
