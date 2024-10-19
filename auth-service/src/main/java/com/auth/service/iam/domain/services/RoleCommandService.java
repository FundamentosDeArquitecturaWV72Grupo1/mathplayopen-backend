package com.auth.service.iam.domain.services;

import com.auth.service.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
