package com._squared.mathplayopen.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String email, String password, List<String> roles) {
}
