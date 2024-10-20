package com.games.service.mathplayopen.domain.model.commands;

import com.games.service.mathplayopen.domain.model.valueobjects.Description;
import com.games.service.mathplayopen.domain.model.valueobjects.Difficulty;
import com.games.service.mathplayopen.domain.model.valueobjects.EmbedCode;
import com.games.service.mathplayopen.domain.model.valueobjects.Title;

public record CreateGameCommand(
    Title title,
    Description description,
    EmbedCode embedCode,
    String imageUrl,
    String rules,
    Difficulty difficulty,
    String topic
) {  }
