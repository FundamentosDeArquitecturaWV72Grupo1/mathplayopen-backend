package com._squared.mathplayopen.iam.domain.services;

import com._squared.mathplayopen.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
