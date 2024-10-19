package com.auth.service.iam.domain.model.queries;

import com.auth.service.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
