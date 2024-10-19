package com.auth.service.iam.domain.model.commands;

import com.auth.service.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password,List<Role> roles) {
}
