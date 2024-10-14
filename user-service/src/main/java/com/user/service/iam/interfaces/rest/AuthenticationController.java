package com.user.service.iam.interfaces.rest;

import com.user.service.iam.domain.services.UserCommandService;
import com.user.service.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.user.service.iam.interfaces.rest.resources.SignInResource;
import com.user.service.iam.interfaces.rest.resources.SignUpResource;
import com.user.service.iam.interfaces.rest.resources.UserResource;
import com.user.service.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.user.service.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.user.service.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.user.service.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller
 * This controller is responsible for handling all the requests related to authentication
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

    private final UserCommandService userCommandService;



    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }



    /**
     * Handles the sign-in request
     * @param signInResource the sign-in request body
     * @return the authenticated user resource
     */
    @PostMapping("/sign-in")

    public ResponseEntity<AuthenticatedUserResource> signin(@RequestBody SignInResource signInResource) {

        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);

        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var authenticateUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticateUserResource);

    }



    /**
     * Handles the sign-up request
     * @param signUpResource the sign-up request body
     * @return the created user resource
     */
    @PostMapping("/sign-up")

    public ResponseEntity<UserResource> singUp(@RequestBody SignUpResource signUpResource){

        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);

    }

}