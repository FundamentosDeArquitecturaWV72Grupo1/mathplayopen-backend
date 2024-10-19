package com.user.service.mathplayopen.domain.model.events;

import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;

public record InstructorRegisteredEvent(Long id, Name name, EmailAddress email, Long institutionId) {
}
