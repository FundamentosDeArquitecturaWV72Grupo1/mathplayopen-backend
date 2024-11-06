package com.auth.service.iam.interfaces.rest.transform;

import com.auth.service.iam.domain.model.commands.SignInCommand;
import com.auth.service.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
