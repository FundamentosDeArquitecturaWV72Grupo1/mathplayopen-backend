package com._squared.mathplayopen.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, String phoneNumber, List<String> roles) {
}
