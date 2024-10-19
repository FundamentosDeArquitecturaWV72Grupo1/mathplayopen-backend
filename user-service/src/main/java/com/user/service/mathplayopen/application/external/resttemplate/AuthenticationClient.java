package com.user.service.mathplayopen.application.external.resttemplate;

import com.user.service.mathplayopen.application.external.feignclient.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class AuthenticationClient {
    private final RestTemplate restTemplate;
    private final String gatewayUrl;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationClient.class);


    @Autowired
    public AuthenticationClient(RestTemplate restTemplate, @Value("${gateway.url}") String gatewayUrl) {
        this.restTemplate = restTemplate;
        this.gatewayUrl = gatewayUrl;
    }

    public UserDto getCurrentUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            log.info("Sending request to AuthService with token: {}", token);
            ResponseEntity<UserDto> response = restTemplate.exchange(
                    gatewayUrl + "/api/v1/authentication/me",
                    HttpMethod.GET,
                    request,
                    UserDto.class);
            log.info("Received response from AuthService: {}", response.getStatusCode());
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error communicating with AuthService: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return null;
        }
    }

    public boolean hasRole(String token, String role) {
        UserDto userDto = getCurrentUser(token);
        return userDto != null && userDto.getRoles().stream().anyMatch(r -> r.getName().equals(role));
    }
}
