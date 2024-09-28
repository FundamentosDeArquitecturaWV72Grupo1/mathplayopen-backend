package com._squared.mathplayopen.iam.interfaces.rest.transform;

import com._squared.mathplayopen.iam.domain.model.entities.Role;
import com._squared.mathplayopen.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
