package com.user.service.mathplayopen.domain.model.commands;

import com.user.service.mathplayopen.domain.model.valueobjects.Address;

public record CreateInstitutionCommand(String name, Address address) {
}
