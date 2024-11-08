package com.games.service.mathplayopen.application.external.feignclients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                final String API_KEY_SIMULATOR = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwZWRyb0BnbWFpbC5jb20iLCJpYXQiOjE3MzEwODA2NjMsImV4cCI6MTczMTY4NTQ2M30.uWIacI3NmuYHRtn7HGJw1rb0J8tL2qrKTUowog8GgvLC8zYuRngwt3OfGNFBKZTI";
                template.header("Authorization", "Bearer " + API_KEY_SIMULATOR);
            }
        };
    }
}
