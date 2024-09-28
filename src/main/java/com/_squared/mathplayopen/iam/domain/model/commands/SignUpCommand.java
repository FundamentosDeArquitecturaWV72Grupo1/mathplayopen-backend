package com._squared.mathplayopen.iam.domain.model.commands;

import com._squared.mathplayopen.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String email, String password,List<Role> roles) {
}
