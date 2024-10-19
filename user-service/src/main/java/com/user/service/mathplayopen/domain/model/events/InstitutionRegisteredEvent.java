package com.user.service.mathplayopen.domain.model.events;

import com.user.service.mathplayopen.domain.model.valueobjects.Address;

public record InstitutionRegisteredEvent(Long id, String name, Address address) {
}
