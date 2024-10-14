package com.user.service.iam.domain.services;

import com.user.service.iam.domain.model.entities.Role;
import com.user.service.iam.domain.model.queries.GetAllRolesQuery;
import com.user.service.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
