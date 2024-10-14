package com.user.service.iam.interfaces.rest.transform;

import com.user.service.iam.domain.model.commands.SignInCommand;
import com.user.service.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
