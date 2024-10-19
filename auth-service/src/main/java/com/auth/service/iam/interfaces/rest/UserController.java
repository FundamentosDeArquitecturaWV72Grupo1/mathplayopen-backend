package com.auth.service.iam.interfaces.rest;

import com.auth.service.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.auth.service.iam.domain.model.queries.GetAllUsersQuery;
import com.auth.service.iam.domain.model.queries.GetUserByIdQuery;
import com.auth.service.iam.domain.services.UserQueryService;
import com.auth.service.iam.interfaces.rest.resources.UserResource;
import com.auth.service.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandServiceImpl userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandServiceImpl userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    /**
     * Get all users
     * @return list of user resources
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers(){

        var getAllUsersQuery = new GetAllUsersQuery();
        var users= userQueryService.handle(getAllUsersQuery);
        var userResources= users.stream().map(
                UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * this method returns the user with the given id
     * @param userId the user id
     * @return the user resource with the given id
     */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {

        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                UserResourceFromEntityAssembler.toResourceFromEntity(user.get())
        );

    }
}