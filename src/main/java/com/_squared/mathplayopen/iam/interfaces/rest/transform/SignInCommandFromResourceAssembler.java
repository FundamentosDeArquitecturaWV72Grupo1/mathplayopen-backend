package com._squared.mathplayopen.iam.interfaces.rest.transform;

import com._squared.mathplayopen.iam.domain.model.commands.SignInCommand;
import com._squared.mathplayopen.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
