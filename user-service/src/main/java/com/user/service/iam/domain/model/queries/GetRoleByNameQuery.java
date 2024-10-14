package com.user.service.iam.domain.model.queries;

import com.user.service.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
