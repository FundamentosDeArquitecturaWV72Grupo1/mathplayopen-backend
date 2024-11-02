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
                String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwZWRyb0BnbWFpbC5jb20iLCJpYXQiOjE3MzA1MTg2MDgsImV4cCI6MTczMTEyMzQwOH0.VjmQHRqqzXlQqJp5AcImKLrq4cmql3OQxioY4G7hlQNAu9TBDIVU_c5HJBbqxRGD";
                template.header("Authorization", "Bearer " + token);
            }
        };
    }
}
