package com._squared.mathplayopen.iam.interfaces.rest.transform;

import com._squared.mathplayopen.iam.domain.model.aggregates.User;
import com._squared.mathplayopen.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), token);
    }
}
