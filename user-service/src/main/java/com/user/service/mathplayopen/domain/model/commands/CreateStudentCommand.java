package com.user.service.mathplayopen.domain.model.commands;

import com.user.service.mathplayopen.domain.model.valueobjects.EmailAddress;
import com.user.service.mathplayopen.domain.model.valueobjects.Name;

public record CreateStudentCommand(Long userId, Name name, EmailAddress email, Long institutionId) {
}
