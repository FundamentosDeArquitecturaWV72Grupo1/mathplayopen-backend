package com.auth.service.iam.interfaces.rest.transform;

import com.auth.service.iam.domain.model.entities.Role;
import com.auth.service.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
