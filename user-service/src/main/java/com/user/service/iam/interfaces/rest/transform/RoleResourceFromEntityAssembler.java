package com.user.service.iam.interfaces.rest.transform;

import com.user.service.iam.domain.model.entities.Role;
import com.user.service.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
