package com._squared.mathplayopen.iam.interfaces.rest.transform;

import com._squared.mathplayopen.iam.domain.model.commands.SignUpCommand;
import com._squared.mathplayopen.iam.domain.model.entities.Role;
import com._squared.mathplayopen.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(resource.email(), resource.password(),roles);
    }
}
