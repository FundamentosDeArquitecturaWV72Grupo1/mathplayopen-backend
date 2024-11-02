package com.review.service.review.application.external.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto {
    private Long id;
    private String title;
    private String description;
    private String embedCode;
    private String imageUrl;
    private String rules;
    private String topic;
}
