package com.games.service.mathplayopen.application.external.feignclients.client;

import com.games.service.mathplayopen.application.external.feignclients.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserServiceFeignClient {
    @GetMapping("/api/v1/students/current")
    UserDto getCurrentUser(@RequestHeader("Authorization") String token);
}
